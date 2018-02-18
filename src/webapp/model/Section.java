package webapp.model;

public abstract class Section {
    private SectionType title;

    public String getTitle() {
        return title.getTitle();
    }

    public abstract void setContent(Object content);
    public abstract String getContent();


    Section(SectionType title) {
        this.title = title;
    }
}
