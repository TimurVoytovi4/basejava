package webapp.storage;

public class ObjectFileStreamStorageTest extends AbstractStorageTest {
    public ObjectFileStreamStorageTest() {
        super(new AbstractFileStorage(STORAGE_DIR, new ObjectStreamStorage()) {
        });
    }
}
