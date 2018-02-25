package webapp.model;

import java.util.List;

public class Organization {
    private final Link homePage;
    private final List<PeriodOrganization> periodOrganizations;

    public Organization(String name, String url, List<PeriodOrganization> periodOrganizations) {
        this.homePage = new Link(name, url);
        this.periodOrganizations = periodOrganizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        return periodOrganizations != null ? periodOrganizations.equals(that.periodOrganizations) : that.periodOrganizations == null;
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + (periodOrganizations != null ? periodOrganizations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", periodOrganizations=" + periodOrganizations +
                '}';
    }
}