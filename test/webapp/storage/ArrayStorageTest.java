package webapp.storage;


import org.junit.Test;
import webapp.exception.StorageException;

public class ArrayStorageTest extends AbstractArrayStorageTest{
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
    @Test(expected = StorageException.class)
    public void saveOverflow(){
        super.saveOverflow();
    }
}