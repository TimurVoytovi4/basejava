package webapp.model;

public class TextField {
    private SectionType textTitle;
    private String textContent;

    TextField(SectionType title) {
        this.textTitle = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public SectionType getTextTitle() {
        return textTitle;
    }

    @Override
    public String toString() {
        return "TextField{" +
                "textTitle='" + textTitle + '\'' +
                ", textContent='" + textContent + '\'' +
                '}';
    }
}
