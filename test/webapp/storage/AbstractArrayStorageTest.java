package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.lang.reflect.Field;

public abstract class AbstractArrayStorageTest {
    private Storage storage;


    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static Resume r1 = new Resume(UUID_4);
    private static Resume r2 = new Resume(UUID_2);

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(r2);
        Assert.assertEquals(r2, storage.get(UUID_2));

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(r2, storage.get(UUID_2));
    }

    @Test
    public void getAll() {
        for (Resume resume : storage.getAll()) {
            switch (resume.getUuid()) {
                case UUID_1:
                    break;
                case UUID_2:
                    break;
                case UUID_3:
                    break;
                default:
                    throw new NotExistStorageException(resume.getUuid());
            }
        }
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void save() {
        storage.save(r1);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws NoSuchFieldException, IllegalAccessException {
        Field field = AbstractArrayStorage.class.getDeclaredField("STORAGE_LIMIT");
        field.setAccessible(true);
        int value = (int) field.get(storage);
        for (int i = 3; i < value; i++) {
            storage.save(new Resume());
        }
        storage.save(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist(){
        storage.save(r2);
    }
}