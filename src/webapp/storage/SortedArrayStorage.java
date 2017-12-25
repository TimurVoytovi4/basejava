package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (checkup(r.getUuid())){
            System.out.println("Resume" + r.getUuid() + " already exist");
        }else if (size + 1 <= STORAGE_LIMIT){
            if (-index > size) {
                increase(r);
            } else {
                System.arraycopy(storage, -index - 1, storage, -index, size);
                storage[-index -1] = r;
                size++;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        if (!checkup(uuid)) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            reduce();
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
