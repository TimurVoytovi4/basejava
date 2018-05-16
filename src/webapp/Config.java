package webapp;

import java.io.*;
import java.util.Properties;

public class Config {

    private static final File PROPS = new File("/home/timur/IdeaProjects/basejava/config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private Properties properties = new Properties();
    private File storageDir;
    private String dbUrl;
    private String dbUsr;
    private String dbPswd;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            dbUrl = properties.getProperty("db.url");
            dbUsr = properties.getProperty("db.user");
            dbPswd = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsr() {
        return dbUsr;
    }

    public String getDbPswd() {
        return dbPswd;
    }
}
