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

    private Map<Type, String> contacts = new HashMap<>();

    private Section personal = new TextField(SectionType.PERSONAL);
    private Section objective = new TextField(SectionType.OBJECTIVE);
    private Section achievement = new TextItem(SectionType.ACHIEVEMENT);
    private Section qualification = new TextItem(SectionType.QUALIFICATIONS);
    private Section experience = new DateTextItems(SectionType.EXPERIENCE);
    private Section education = new DateTextItems(SectionType.EDUCATION);

    public void setSectionContent(Section section, String text, String namePlace, LocalDate start, LocalDate end, String position) {
        if (section.equals(experience) || section.equals(education)) {
            DateTextItems.PlaceOfStay obj = new DateTextItems.PlaceOfStay(text, namePlace, start, end, position);
            section.setContent(obj);
        }
        section.setContent(text);
    }

    public String getSection(Section section) {
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
