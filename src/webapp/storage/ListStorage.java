package webapp.storage;

import webapp.model.Resume;

import java.util.Iterator;

public class ListStorage extends AbstractStorage {

    @Override
    protected int getIndex(String uuid) {
        Iterator <Resume> itr = storage.iterator();
        int index= 0;
        while (itr.hasNext()){
            Resume r = itr.next();
            if (r.getUuid().equals(uuid)){
                return index;
            }
            index++;
        }
        return index;
    }
}
