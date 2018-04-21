package webapp.storage;

import webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()) {
        });
    }
}
