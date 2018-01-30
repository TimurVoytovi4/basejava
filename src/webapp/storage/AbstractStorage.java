package webapp.storage;


import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger log = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void doDelete(SK  searchKey);

    protected abstract void doSave(Resume r, SK  searchKey);

    protected abstract boolean isExist(SK  searchKey);

    protected abstract Resume doGet(SK  searchKey);

    protected abstract SK  getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, SK  searchKey);

    protected abstract List<Resume> doCopyAll();

    @Override
    public Resume get(String uuid) {
        log.info("Get " + uuid);
        SK  searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        log.info("Update " + r);
        SK  searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        log.info("Save " + r);
        SK  searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        log.info("Delete " + uuid);
        SK  searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK  getExistedSearchKey(String uuid) {
        SK  searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            log.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        log.info("GetAllSorted ");
        List<Resume> resumeList = doCopyAll();
        Collections.sort(resumeList);
        return resumeList;
    }


    private SK  getNotExistedSearchKey(String uuid) {
        SK  searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            log.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
