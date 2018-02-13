package webapp.model;

import java.util.HashMap;
import java.util.Map;

public class Contacts {
    private Map<String, String> contactTable = new HashMap<>();

    public void setContacts(String idContact, String valContact) {
        contactTable.put(idContact, valContact);
    }

    public Map<String, String> getContactTable() {
        return contactTable;
    }
}
