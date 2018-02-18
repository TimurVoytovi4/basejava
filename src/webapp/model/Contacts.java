package webapp.model;

public enum Contacts {
    ID("Тип");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
