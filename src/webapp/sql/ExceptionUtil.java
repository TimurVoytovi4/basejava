package webapp.sql;

import org.postgresql.util.PSQLException;
import webapp.exception.ExistStorageException;
import webapp.exception.StorageException;

import java.sql.SQLException;

class ExceptionUtil {
    private ExceptionUtil() {
    }

    static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}