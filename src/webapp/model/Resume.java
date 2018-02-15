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

    private Map<Contacts, Contacts> contacts = new HashMap<>();

    private TextField personal = new TextField(SectionType.PERSONAL);
    private TextField objective = new TextField(SectionType.OBJECTIVE);
    private TextItem achievement = new TextItem(SectionType.ACHIEVEMENT);
    private TextItem qualification = new TextItem(SectionType.QUALIFICATIONS);
    private DateTextItems experience = new DateTextItems(SectionType.EXPERIENCE);
    private DateTextItems education = new DateTextItems(SectionType.EDUCATION);

    public void setContacts(Contacts id, Contacts value) {
        contacts.put(id, value);
    }

    public Map<Contacts, Contacts> getContacts() {
        return contacts;
    }

    public TextField getPersonal() {
        return personal;
    }

    public void setPersonal(String text) {
        personal.setTextContent(text);
    }

    public TextField getObjective() {
        return objective;
    }

    public void setObjective(String text) {
        objective.setTextContent(text);
    }


    public TextItem getAchievement() {
        return achievement;
    }

    public void setAchievement(String text) {
        achievement.setList(text);
    }

    public TextItem getQualification() {
        return qualification;
    }

    public void setQualification(String text) {
        qualification.setList(text);
    }

    public DateTextItems getExperience() {
        return experience;
    }

    public void setExperience(String namePlace, LocalDate start, LocalDate end, String position, String description) {
        DateTextItems.PlaceOfStay obj = new DateTextItems.PlaceOfStay(namePlace, start, end, position, description);
        experience.setList(obj);
    }

    public DateTextItems getEducation() {
        return education;
    }

    public void setEducation(String namePlace, LocalDate start, LocalDate end, String position) {
        DateTextItems.PlaceOfStay obj = new DateTextItems.PlaceOfStay(namePlace, start, end, position, null);
        education.setList(obj);
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
