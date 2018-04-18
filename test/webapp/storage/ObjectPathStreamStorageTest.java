package webapp.storage;

public class ObjectPathStreamStorageTest extends AbstractStorageTest {
    public ObjectPathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()) {
        });
    }
}
