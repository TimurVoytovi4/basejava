
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++)
            storage[i] = null;
            size = 0;
    }

    void save(Resume r) {
        if (r.uuid != null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        Resume r = new Resume();
        for (int i = 0; i < size -1; i++)
            if (storage[i].uuid.equals(uuid)) {
            r = storage[i];
            }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++)
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size];
                size--;
            }
        }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume [] res = new Resume[size];
        System.arraycopy(storage, 0,res,0,size);
        return res;
    }

    int size() {
       return size;
    }
}
