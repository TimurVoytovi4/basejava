
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
        if (r.uuid != null) {
            for (int i = 0; i < size; i++) {
                if (r.uuid.equals(storage[i].uuid)) {
                    storage[i] = r;
                    System.out.println("Успешно обновлено");
                    return;
                }
            }
            System.out.println("Ошибка:нет совпадений");
        }
    }

    void save(Resume r) {
        if (r.uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    System.out.println("Ошибка:такая запись уже сушествует");
                    return;
                }
            }
            if (size + 1 <= storage.length) {
                storage[size] = r;
                size++;
            }
        }
    }

    Resume get(String uuid) {
        Resume temp = new Resume();
        for (int i = 0; i < size; i++)
            if (storage[i].uuid.equals(uuid)) {
                temp = storage[i];
            }
        return temp;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++)
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
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
}
