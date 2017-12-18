package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else System.out.println("Resume" +r.getUuid() + " not exist");
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume" + r.getUuid() + " already exist");
        } else if (size + 1 <= STORAGE_LIMIT) {
            storage[size] = r;
            size++;
        }else {
            System.out.println("Storage overflow");
        }
    }



    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }else System.out.println("Resume" + uuid + " not exist");
    }

    /**
     * @return array, contains only Resumes in webapp.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
