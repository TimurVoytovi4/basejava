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

    public <T> T sqlExecute(String str, SqlExecutor<T> executer) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            return executer.queryExecute(preparedStatement);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }
    public <T> T transactionalExecute(SqlTransaction<T> executor){
        try (Connection connection = connectionFactory.getConnection()){
            try {
                connection.setAutoCommit(false);
                T res = executor.execute(connection);
                connection.commit();
                return res;
            }catch (SQLException e){
                connection.rollback();
                throw ExceptionUtil.convertException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}