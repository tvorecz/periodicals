package by.training.zorich.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -4031341222140043750L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception exeption) {
        super(message, exeption);
    }

    public ServiceException(Exception exeption) {
        super(exeption);
    }
}
