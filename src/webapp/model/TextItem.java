package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextItem {
    private SectionType textItemTitle;
    private List<String> list = new ArrayList<>();

    TextItem(SectionType textItemTitle) {

        this.textItemTitle = textItemTitle;
    }

    public SectionType getTextItemTitle() {
        return textItemTitle;
    }

    public void setList(String item){
        list.add(item);
    }

    public List<String> getList() {
        return list;
    }
}
