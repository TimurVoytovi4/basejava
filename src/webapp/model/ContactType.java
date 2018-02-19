package webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    EMAIL("Почта"),
    GITHUB("Аккаунт"),
    SKYPE("Аккаунт");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
