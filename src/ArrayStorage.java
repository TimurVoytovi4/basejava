import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int i = getIndex(r.uuid);
        if (i != -1) {
            storage[i] = r;
            System.out.println("Успешно обновлено");
        } else System.out.println("Ошибка:нет совпадений");
    }

    void save(Resume r) {
        if (getIndex(r.uuid) != -1) {
            System.out.println("Ошибка:такая запись уже сушествует");
        } else if (size + 1 <= storage.length) {
            storage[size] = r;
            size++;
        }
    }


    Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            return storage[i];
        }
        return null;

    }

    void delete(String uuid) {
        int i = getIndex(uuid);
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
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }

    private int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid != null && storage[i].uuid.equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
