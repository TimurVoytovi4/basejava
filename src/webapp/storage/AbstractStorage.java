package webapp.storage;

import webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {
    List<Resume> storage;

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    public void delete(String uuid) {
        storage.remove(getIndex(uuid));
    }

    public Resume get(String uuid) {
        return storage.get(getIndex(uuid));
    }

    public void save(Resume resume) {
        storage.add(resume);
    }

    public void update(Resume resume) {
        storage.set(getIndex(resume.getUuid()), resume);
    }

    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    protected abstract int getIndex(String uuid);
}
