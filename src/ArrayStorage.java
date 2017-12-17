import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
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

    public void save(Resume r) {
        if (getIndex(r.uuid) != -1) {
            System.out.println("Ошибка:такая запись уже сушествует");
        } else if (size + 1 <= STORAGE_LIMIT) {
            storage[size] = r;
            size++;
        }
    }


    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            return storage[i];
        }
        return null;

    }

    public void delete(String uuid) {
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
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid != null && storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
