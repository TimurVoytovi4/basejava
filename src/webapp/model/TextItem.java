package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextItem {
    private String textItemTitle;
    private List<String> list = new ArrayList<>();

    TextItem(String textItemTitle) {

        this.textItemTitle = textItemTitle;
    }

    public String getTextItemTitle() {
        return textItemTitle;
    }

    public void setList(String item){
        list.add(item);
    }

    public List<String> getList() {
        return list;
    }
}
