
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static int j = 0;
    Resume[] storage = new Resume[10];

    void clear() {
        for (int i = 0; i < storage.length; i++)
            storage[i] = null;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                return;
            }
        }
    }


    Resume get(String uuid) {
        Resume r = new Resume();
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].uuid == uuid) {
            r = storage[i];
            return r;
            }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].uuid == uuid) {
                storage[i] = null;
                return;
            }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null){
                j++;
            }
        }
        Resume [] res = new Resume[j];
        int k = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                res[k] = storage[i];
                k++;
            }
        }
        j = 0;
        return res;
    }


    int size() {
        int k = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) k++;
        }
        return k;
    }
}
