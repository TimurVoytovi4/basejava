
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

    public void update(Resume r) {
        int i = iterator(r.uuid);
        if (i != -1) {
            storage[i] = r;
            System.out.println("Успешно обновлено");
        } else System.out.println("Ошибка:нет совпадений");
    }

    void save(Resume r) {
        if (r != null) {
            if (iterator(r.uuid) != -1) {
                System.out.println("Ошибка:такая запись уже сушествует");
            } else if (size + 1 <= storage.length) {
                storage[size] = r;
                size++;
            }
        }
    }


    Resume get(String uuid) {
        int i = iterator(uuid);
        if (i != -1) {
            return storage[i];
        }
        return null;

    }

    void delete(String uuid) {
        int i = iterator(uuid);
        if (i != -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[size];
        System.arraycopy(storage, 0, res, 0, size);
        return res;
    }

    int size() {
        return size;
    }

    private int iterator(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid != null && storage[i].uuid.equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
