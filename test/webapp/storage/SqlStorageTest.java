package webapp.storage;


import webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUsr(), Config.get().getDbPswd()) {
        });
    }
}
