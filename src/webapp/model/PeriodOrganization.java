package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodOrganization {
    private final LocalDate start;
    private final LocalDate end;
    private final String title;
    private final String description;

    public PeriodOrganization(LocalDate start, LocalDate end, String title, String description) {
        Objects.requireNonNull(start, "start must not be null");
        Objects.requireNonNull(end, "end must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodOrganization periodOrganization = (PeriodOrganization) o;

        if (!start.equals(periodOrganization.start)) return false;
        if (!end.equals(periodOrganization.end)) return false;
        if (!title.equals(periodOrganization.title)) return false;
        return description != null ? description.equals(periodOrganization.description) : periodOrganization.description == null;
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PeriodOrganization{" +
                "start=" + start +
                ", end=" + end +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
