package webapp.storage;

import org.junit.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;

import static org.junit.Assert.fail;

public class AbstractArrayStorageTest extends AbstractStorageTest{
    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }
    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("test " + String.valueOf(i)));
            }
        } catch (StorageException e) {
            fail();
        }
        storage.save(new Resume("test last"));
    }
}
