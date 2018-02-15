package webapp.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DateTextItems {

    private SectionType dateTextItemsTitle;

    public SectionType getDateTextItemsTitle() {
        return dateTextItemsTitle;
    }
    DateTextItems(SectionType dateTextItemsTitle) {
        this.dateTextItemsTitle = dateTextItemsTitle;
    }

    private List<PlaceOfStay> list = new LinkedList<>();

    public void setList(PlaceOfStay obj) {
        list.add(obj);
    }

    public List<PlaceOfStay> getList() {
        return list;
    }

    class PlaceOfStay {
        String namePlace;
        LocalDate start;
        LocalDate end;
        String position;
        String description;

        public PlaceOfStay(String namePlace, LocalDate start, LocalDate end, String position, String description) {
            this.namePlace = namePlace;
            this.start = start;
            this.end = end;
            this.position = position;
            this.description = description;
        }

    }
}
