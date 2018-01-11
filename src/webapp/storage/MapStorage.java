package webapp.storage;

import webapp.model.Resume;

public class MapStorage extends AbstractStorage {
    @Override
    protected Resume foundItem(String uuid) {
        return null;
    }

    @Override
    protected boolean isOverflow(Resume r) {
        return false;
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void insertElement(Resume r, int index) {

    }

    @Override
    protected void fillDeletedElement(int index) {

    }

    @Override
    protected void nullRemovedElem(int index) {

    }

    @Override
    void replace(int index, Resume r) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
