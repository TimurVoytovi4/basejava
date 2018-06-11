package webapp.storage;

import webapp.exception.NotExistStorageException;
import webapp.model.*;
import webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.sqlExecute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
                removeResumeContact(r, connection);
                setResumeContact(r, connection);
                removeResumeSection(r, connection);
                setResumeSection(r, connection);
                return null;
            }
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume(uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            setResumeContact(r, connection);
            setResumeSection(r, connection);
            return null;
        });
    }


    @Override
    public Resume get(String uuid) {
        return sqlHelper.sqlExecute("SELECT *\n" +
                        "FROM resume r\n" +
                        "LEFT JOIN contact c\n" +
                        "    ON r.uuid = c.resume_uuid\n" +
                        "WHERE r.uuid =?",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, resultSet.getString("full_name"));
                    do {
                        addResumeContact(r, resultSet);
                    } while (resultSet.next());
                    return r;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.sqlExecute("DELETE FROM resume WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> list = new LinkedHashMap<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    list.put(uuid, new Resume(uuid, resultSet.getString("full_name")));
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Resume resume = list.get(resultSet.getString("resume_uuid"));
                    addResumeContact(resume, resultSet);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Resume resume = list.get(resultSet.getString("resume_uuid"));
                    addResumeSection(resume, resultSet);
                }
            }
            return new ArrayList<>(list.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.sqlExecute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }

    private void addResumeContact(Resume resume, ResultSet resultSet) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null)
            resume.addContact(ContactType.valueOf(resultSet.getString("type")), value);
    }

    private void addResumeSection(Resume resume, ResultSet resultSet) throws SQLException {
        String content = resultSet.getString("content");
        SectionType type = SectionType.valueOf(resultSet.getString("type"));
        Section section = null;
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(content);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = new ListSection(Arrays.asList(content.split("\n")));
                break;
        }
        if (content != null) {
            resume.addSection(type, section);
        }
    }

    private void removeResumeContact(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void removeResumeSection(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void setResumeContact(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact(resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : r.getContact().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void setResumeSection(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section(resume_uuid, type, content) VALUES (?,?,?)")) {
            String section = null;
            for (Map.Entry<SectionType, Section> entry : r.getSection().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = entry.getValue().toString();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = entry.getValue().toString();
                        break;
                }
                ps.setString(3, section);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
