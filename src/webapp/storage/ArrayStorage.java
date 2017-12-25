package webapp.storage;

import webapp.model.Resume;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (checkup(r.getUuid())) {
            System.out.println("Resume" + r.getUuid() + " already exist");
        } else if (size + 1 <= STORAGE_LIMIT) {
            increase(r);
        } else {
            System.out.println("Storage overflow");
        }
    }

    public void delete(String uuid) {
        if (checkup(uuid)) {
            storage[index] = storage[size - 1];
            reduce();
        } else System.out.println("Resume " + uuid + " not exist");
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
