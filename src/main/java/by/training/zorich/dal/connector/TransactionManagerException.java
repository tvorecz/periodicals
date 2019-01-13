package by.training.zorich.dal.connector;

public class TransactionManagerException extends Exception {
    private static final long serialVersionUID = -5511879390767010050L;

    public TransactionManagerException() {
        super();
    }

    public TransactionManagerException(String message) {
        super(message);
    }

    public TransactionManagerException(String message, Exception exeption) {
        super(message, exeption);
    }

    public TransactionManagerException(Exception exeption) {
        super(exeption);
    }
}
