package webapp.storage;

import webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected void doSave(Resume r, Object searchKey) {

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
