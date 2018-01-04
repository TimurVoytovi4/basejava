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

    @Before
    public void setUp() throws Exception{
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
    }

    @Test
    public void update() {
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}