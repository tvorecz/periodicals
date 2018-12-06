package by.training.zorich.dal.exception;

public class ExecutorException extends Exception {
    private static final long serialVersionUID = -978596033321907975L;

    public ExecutorException() {
        super();
    }

    public ExecutorException(String message) {
        super(message);
    }

    public ExecutorException(String message, Exception exeption) {
        super(message, exeption);
    }

    public ExecutorException(Exception exeption) {
        super(exeption);
    }
}
