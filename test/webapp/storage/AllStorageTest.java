package webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webapp.storage.serializer.JsonStreamSerializer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class,
        XmlPathStorageTest.class,
        SqlStorageTest.class

})
public class AllStorageTest {
}
