package webapp.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.webapp.model.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private Map<ContactType, String> contacts = new HashMap<>();
    private Map<SectionType, Section> sectionMap = new HashMap<>();
    private Section[] sections = new Section[6];

    void create() {
        sections[0] = new TextField();
        sections[1] = new TextField();
        sections[2] = new TextItem();
        sections[3] = new TextItem();
        sections[4] = new DateTextItems();
        sections[5] = new DateTextItems();
    }

    void addedToMap() {
        for (SectionType type : SectionType.values()) {
            int i = -1;
            i++;
            sectionMap.put(type, sections[i]);
        }
    }

    public void setSectionMap(SectionType sectionType, String content, String namePlace, LocalDate start, LocalDate end, String position) {
        Section section = sectionMap.get(sectionType);
        if (section.equals(sections[4]) || section.equals(sections[5])) {
            DateTextItems.PlaceOfStay obj = new DateTextItems.PlaceOfStay(content, namePlace, start, end, position);
            section.setContent(obj);
        } else {
            section.setContent(content);
        }
        sectionMap.replace(sectionType, section);
    }

    public String getSection(SectionType searchSection) {
        Section section = sectionMap.get(searchSection);
        return section.getContent();
    }

    public String getFullName() {
        return fullName;
    }

    public Resume(String fullName) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
