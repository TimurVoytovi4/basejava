package webapp.storage;


import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    int size = 0;

    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return foundItem(uuid);
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            replace(index, r);
        } else throw new NotExistStorageException(r.getUuid());
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            nullRemovedElem(index);
            size--;
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (!isOverflow(r)) {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract Resume foundItem(String uuid);

    protected abstract boolean isOverflow(Resume r);

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract void nullRemovedElem(int index);

    abstract void replace(int index, Resume r);
}
