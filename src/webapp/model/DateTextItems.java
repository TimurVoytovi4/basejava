package webapp.model;

import java.util.LinkedList;
import java.util.List;

public class DateTextItems {
    private List<PlaceOfStay> list = new LinkedList<>();

    public void setList(PlaceOfStay obj) {
        list.add(obj);
    }

    public List<PlaceOfStay> getList() {
        return list;
    }

    class PlaceOfStay {
        String namePlace;
        String date;
        String position;
        String description;

        public PlaceOfStay(String namePlace, String date, String position, String description) {
            this.namePlace = namePlace;
            this.date = date;
            this.position = position;
            this.description = description;
        }
    }
}
