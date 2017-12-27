package webapp.storage;

import webapp.model.Resume;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    public void fillDeletedElement(int index) {
            storage[index] = storage[size - 1];
    }

    /**
     * @return array, contains only Resumes in webapp.storage (without null)
     */

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
