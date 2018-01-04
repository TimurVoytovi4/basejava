package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;


    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp(){
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
        storage.update(new Resume(UUID_2));
    }

    @Test
    public void size(){
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        storage.get(UUID_1);

    }

    @Test
    public void getAll() {
        storage.getAll();
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist(){
        storage.get("dummy");
    }
}