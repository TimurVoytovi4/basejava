package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    int index;
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

    private boolean checkup(String uuid) {
        index = getIndex(uuid);
        return index >= 0;
    }

    @Override
    public void delete(String uuid) {
        if (!checkup(uuid)) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void save(Resume r) {
        if (checkup(r.getUuid())) {
            System.out.println("Resume" + r.getUuid() + " already exist");
        } else if (size + 1 <= STORAGE_LIMIT) {
            fillSavedElement(r);
            size++;
            index = 0;
        } else {
            System.out.println("Storage overflow");
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void fillSavedElement(Resume r);
}
