package by.jwd.task6.dao;

public class DaoException extends Exception {

    private static final long serialVersionUID = -6900710934523202568L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
