package webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.webapp.model.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public Resume(String fullName) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
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
}
