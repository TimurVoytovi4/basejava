package webapp.storage;

import webapp.storage.serializer.JsonStreamSerializer;
import webapp.storage.serializer.XmlStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()) {
        });
    }
}
