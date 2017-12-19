package webapp.model;

import java.util.Objects;

/**
 * com.urise.webapp.webapp.model.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
