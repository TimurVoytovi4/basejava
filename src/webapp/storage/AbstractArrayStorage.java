package webapp.storage;

import webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 100000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            return storage[i];
        }
        return null;
    }

    protected abstract int getIndex(String uuid);

}
