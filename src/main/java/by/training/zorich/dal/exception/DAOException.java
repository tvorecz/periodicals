package by.training.zorich.dal.exception;

public class DAOException extends Exception {
    private static final long serialVersionUID = -1006711814697315510L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception exeption) {
        super(message, exeption);
    }

    public DAOException(Exception exeption) {
        super(exeption);
    }
}
