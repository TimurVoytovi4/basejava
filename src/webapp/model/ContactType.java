package webapp.model;

public enum ContactType {
    PHONE(""),
    EMAIL(""),
    GITHUB(""),
    SKYPE("");


    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
