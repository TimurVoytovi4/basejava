package webapp.sql;

import webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sqlExecute(String str) {
        sqlExecute(str, PreparedStatement::execute);
    }

    public <T> T sqlExecute(String str, QueryExecuter<T> executer) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(str)) {
            return executer.queryExecute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
