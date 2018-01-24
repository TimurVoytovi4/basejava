package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doDelete(Object searchKey) {
        Resume resume = (Resume)searchKey;
        map.remove(resume.getFullName());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected Object getSearchKey(String fullName) {
        for (Map.Entry entry : map.entrySet()){
            Resume resume = (Resume) entry.getValue();
            if (resume.getFullName().equals(fullName))return resume;
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        Resume resume = (Resume)searchKey;
        map.replace(resume.getUuid(), r);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> storage() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
