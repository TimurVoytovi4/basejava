package webapp.storage.serializer;

import webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            listWriter(dos, r.getContact().entrySet(), (Map.Entry<ContactType, String> contact) -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });

            listWriter(dos, r.getSection().entrySet(), (Map.Entry<SectionType, Section> elem) -> {
                SectionType sectionType = elem.getKey();
                Section section = elem.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        listWriter(dos, ((ListSection) section).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        listWriter(dos, ((OrganizationSection) section).getOrganizations(), obj -> {
                            dos.writeUTF(obj.getHomePage().getName());
                            dos.writeUTF(obj.getHomePage().getUrl());
                            listWriter(dos, obj.getPositions(), pos -> {
                                dos.writeInt(pos.getStartDate().getYear());
                                dos.writeInt(pos.getStartDate().getMonth().getValue());
                                dos.writeInt(pos.getEndDate().getYear());
                                dos.writeInt(pos.getEndDate().getMonth().getValue());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }


    private <T> void listWriter(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T obj : collection) {
            writer.write(obj);
        }
    }

    private interface Writer<T> {
        void write(T obj) throws IOException;

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(listReader(dis, dis::readUTF));
            case EDUCATION:
            case EXPERIENCE:
                return new OrganizationSection(listReader(dis, () -> new Organization(
                        new Link(dis.readUTF(), dis.readUTF()),
                        listReader(dis, () -> new Organization.Position(
                                LocalDate.of(dis.readInt(), dis.readInt(), 1),
                                LocalDate.of(dis.readInt(), dis.readInt(), 1),
                                dis.readUTF(),
                                dis.readUTF()
                        )))));
            default:
                throw new IllegalArgumentException();
        }
    }

    private <T> void readItems(DataInputStream dis, Process<T> process) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            process.process();
        }
    }

    private <T> List<T> listReader(DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface Reader<T> {
        T read() throws IOException;
    }

    private interface Process<T> {
        void process() throws IOException;
    }
}
