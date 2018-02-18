package webapp.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DateTextItems extends Section {

    private List<PlaceOfStay> list = new LinkedList<>();

    DateTextItems(SectionType title) {
        super(title);
    }

    public void setContent(Object content) {
        list.add((PlaceOfStay) content);
    }

    public String getContent() {
        return list.toString();
    }

    static class PlaceOfStay {
        String namePlace;
        LocalDate start;
        LocalDate end;
        String position;
        String description;

        PlaceOfStay(String description, String namePlace, LocalDate start, LocalDate end, String position) {
            this.namePlace = namePlace;
            this.start = start;
            this.end = end;
            this.position = position;
            this.description = description;
        }

    }
}
