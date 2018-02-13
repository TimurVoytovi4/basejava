package webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.webapp.model.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Contacts contacts = new Contacts();
    private TextField personal = new TextField(SectionType.PERSONAL.getTitle());
    private TextField objective = new TextField(SectionType.OBJECTIVE.getTitle());
    private TextItem achievement = new TextItem(SectionType.ACHIEVEMENT.getTitle());
    private TextItem qualification = new TextItem(SectionType.QUALIFICATIONS.getTitle());
    private DateTextItems experience = new DateTextItems(SectionType.EXPERIENCE.getTitle());
    private DateTextItems education = new DateTextItems(SectionType.EDUCATION.getTitle());

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
