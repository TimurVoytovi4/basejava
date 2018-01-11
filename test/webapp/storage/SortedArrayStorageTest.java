package webapp.storage;


import org.junit.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;

import static org.junit.Assert.fail;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail();
        }
        storage.save(RESUME_4);
    }
}