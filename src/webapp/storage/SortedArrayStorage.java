package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int i = getIndex(r.getUuid()) * (-1);
        if (i > size) {
            storage[size] = r;
            size++;
        } else {
            System.arraycopy(storage, i - 1, storage, i, size - 1);
            storage[i - 1] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size--] = null;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
