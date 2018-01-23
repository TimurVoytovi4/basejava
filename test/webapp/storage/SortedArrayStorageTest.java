package webapp.storage;


import org.junit.Test;
import webapp.exception.StorageException;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        super.saveOverflow();
    }
}