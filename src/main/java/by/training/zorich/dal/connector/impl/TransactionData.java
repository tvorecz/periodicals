package by.training.zorich.dal.connector.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

public class TransactionData {
    private Connection connection;
    private Map<TransactionDAOOperationType, PreparedStatement> preparedStatementMap;

    public TransactionData() {
        preparedStatementMap = new HashMap<>();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getActualPreparedStatement(TransactionDAOOperationType transactionDAOOperationType) {
        return preparedStatementMap.get(transactionDAOOperationType);
    }

    public void setPreparedStatement(TransactionDAOOperationType transactionDAOOperationType,
                                     PreparedStatement preparedStatement) {
        preparedStatementMap.put(transactionDAOOperationType, preparedStatement);
    }

    public List<PreparedStatement> getAllPreparedStatements() {
        List<PreparedStatement> preparedStatements = new ArrayList<>();

        Iterator<TransactionDAOOperationType> iteratorKeys = preparedStatementMap.keySet().iterator();

        while (iteratorKeys.hasNext()) {
            preparedStatements.add(preparedStatementMap.remove(iteratorKeys.next()));
        }

        return preparedStatements;
    }
}
