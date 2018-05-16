package webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryExecuter<T> {
    T queryExecute(PreparedStatement preparedStatement) throws SQLException;
}
