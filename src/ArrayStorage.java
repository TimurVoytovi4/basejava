
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++)
            storage[i] = null;
    }

    void save(Resume r) {
        if(r.uuid != null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    return;
                }
            }
        }else return;
    }


    Resume get(String uuid) {
        Resume r = new Resume();
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].uuid == uuid) {
            r = storage[i];
            }
        return r;
    }

    void delete(String uuid) {
        Resume[] newStorage = new Resume[10000];
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].uuid == uuid) {
                storage[i] = null;
                int w = 0;
                for (int q = 0; q < storage.length; q++) {
                    if (storage[q] != null) {
                        newStorage[w] = storage[q];
                        w++;
                    }
                    return;
                }
                storage = newStorage;
            }
        }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int j = 0;
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
