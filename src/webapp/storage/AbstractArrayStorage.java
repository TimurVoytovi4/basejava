package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void replace(int index, Resume r) {
        storage[index] = r;
    }

    public Resume foundItem(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void nullRemovedElem(int index) {
        storage[size - 1] = null;
    }

    public boolean isOverflow(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else return false;
    }


}
