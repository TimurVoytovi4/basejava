package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume r, int index) {
        if (-index > size) {
            storage[size] = r;
        } else {
            System.arraycopy(storage, -index - 1, storage, -index, size);
            storage[-index - 1] = r;
        }
    }

    @Override
    public void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
