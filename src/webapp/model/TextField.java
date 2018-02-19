package webapp.model;

public class TextField extends Section {
    private String textContent;

    public void setContent(Object textContent) {
        this.textContent = (String) textContent;
    }

    public String getContent() {
        return textContent;
    }
}
