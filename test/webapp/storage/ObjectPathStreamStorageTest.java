package webapp.storage;

public class ObjectPathStreamStorageTest extends AbstractStorageTest {
    public ObjectPathStreamStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()) {
        });
    }
}
