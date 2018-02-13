package webapp.model;

public class TextField {
    private String textTitle;
    private String textContent;

    TextField(String title) {
        this.textTitle = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextTitle() {
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
