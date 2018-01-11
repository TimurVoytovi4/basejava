package webapp.storage;

import webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(String.valueOf(searchKey));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(null, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(String.valueOf(searchKey));
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace(String.valueOf(searchKey), r);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        return SortResumeByName.sort(list);
    }

    @Override
    public int size() {
        return map.size();
    }
}
