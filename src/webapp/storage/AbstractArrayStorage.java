package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    static final int STORAGE_LIMIT = 100000;
    static int index;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (checkup(r.getUuid())) {
            storage[index] = r;
            index = 0;
        } else System.out.println("Resume" + r.getUuid() + " not exist");
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int temp = getIndex(uuid);
        if (temp >= 0) {
            return storage[temp];
        } else return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    boolean checkup(String uuid) {
        index = getIndex(uuid);
        return index != -1;
    }

    protected abstract int getIndex(String uuid);

}
