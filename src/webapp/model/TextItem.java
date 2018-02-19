package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextItem extends Section {

    private List<String> list = new ArrayList<>();


    public void setContent(Object text){
        list.add((String) text);
    }

    public String getContent() {
        return list.toString();
    }
}
