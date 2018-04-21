package webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class

})
public class AllStorageTest {
}
