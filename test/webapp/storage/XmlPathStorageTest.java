package webapp.storage;

import webapp.storage.serializer.ObjectStreamSerializer;
import webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()) {
        });
    }
}
