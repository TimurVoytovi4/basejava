package webapp.storage;

import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "test1");
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "test2");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "test3");
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "test4");
    Storage storage;

    private Organization org = new Organization("TestOrganizations", "url",
            new ArrayList<>(Arrays.asList(
                    new PeriodOrganization(LocalDate.of(2017, 12, 15),
                            LocalDate.now(), "TestTitle1", "Description1"),
                    new PeriodOrganization(LocalDate.of(2016, 10, 11),
                            LocalDate.of(2017, 12, 15), "TestTitle2", "Description2"))));

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private void addSection(Resume resume) {
        resume.setSections(SectionType.PERSONAL, new TextSection("TestPersonal"));
        resume.setSections(SectionType.OBJECTIVE, new TextSection("TestObjective"));
        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<>(Arrays.asList("TestAchievement1", "TestAchievement2"))));
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<>(Arrays.asList("TestQualification1", "TestQualification2"))));
        resume.setSections(SectionType.EDUCATION, new OrganizationSection(new ArrayList<>(Arrays.asList(org, org))));
        resume.setSections(SectionType.EXPERIENCE, new OrganizationSection(new ArrayList<>(Arrays.asList(org, org))));
    }

    private void addContacts(Resume resume) {
        resume.setContacts(ContactType.PHONE, "322223");
        resume.setContacts(ContactType.MOBILE, "9846432");
        resume.setContacts(ContactType.MAIL, "ert@fr.ru");
        resume.setContacts(ContactType.HOME_PHONE, "498795");
        resume.setContacts(ContactType.SKYPE, "skype");
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        addSection(RESUME_1);
        addContacts(RESUME_1);
        storage.save(RESUME_2);
        addSection(RESUME_2);
        addContacts(RESUME_2);
        storage.save(RESUME_3);
        addSection(RESUME_3);
        addContacts(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "test1_1");
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_2);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }


}