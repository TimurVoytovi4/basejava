package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    abstract void doWrite(Resume r, File file) throws IOException;

    abstract Resume doRead(File file);

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> resumeList = new ArrayList<>();
        for (File file : directory.listFiles())
            resumeList.add(doRead(file));
        return resumeList;
    }

    @Override
    public void clear() {
        for (File file : directory.listFiles())
            if (file.isFile()) file.delete();
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        return files.length;
    }
}
