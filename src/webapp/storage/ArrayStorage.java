package webapp.storage;

import webapp.model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    public void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    public void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
