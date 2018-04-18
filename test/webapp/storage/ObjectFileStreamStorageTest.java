package webapp.storage;

public class ObjectFileStreamStorageTest extends AbstractStorageTest {
    public ObjectFileStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()) {
        });
    }
}
