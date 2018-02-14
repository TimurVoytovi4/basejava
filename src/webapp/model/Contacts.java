package webapp.model;

public enum Contacts {
    ID("Тип"),
    VALUE("Значение");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
