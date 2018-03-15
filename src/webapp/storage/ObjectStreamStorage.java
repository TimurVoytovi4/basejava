package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage{
    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)){
            oos.writeObject(r);
        }
    }

    @Override
    Resume doRead(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)){
            return (Resume)ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume ", null, e);
        }
    }
}