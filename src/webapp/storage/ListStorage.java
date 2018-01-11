package webapp.storage;

import webapp.model.Resume;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new LinkedList<>();


    @Override
    protected Resume foundItem(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected boolean isOverflow(Resume r) {
        return false;
    }

    @Override
    protected int getIndex(String uuid) {
//        Resume resume = new Resume(uuid);
//        return storage.indexOf(resume);
        Resume searchKey = new Resume(uuid);
        Resume[] array = storage.toArray(new Resume[0]);
        return Arrays.binarySearch(array, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int insertIdx = -index - 1;
        storage.add(insertIdx, r);
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage.remove(index);
    }

    @Override
    protected void nullRemovedElem(int index) {

    }

    @Override
    void replace(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    public void clear() {
        storage.clear();
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }
}
