package by.training.zorich.dal.connector;

public class DataSourceConnectorException extends Exception {
    private static final long serialVersionUID = 7976616342829664870L;

    public DataSourceConnectorException() {
        super();
    }

    public DataSourceConnectorException(String message) {
        super(message);
    }

    public DataSourceConnectorException(String message, Exception exeption) {
        super(message, exeption);
    }

    public DataSourceConnectorException(Exception exeption) {
        super(exeption);
    }
}
