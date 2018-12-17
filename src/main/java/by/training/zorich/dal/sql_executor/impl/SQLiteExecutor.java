package by.training.zorich.dal.sql_executor.impl;

import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExecutor implements SQLExecutor {
    public SQLiteExecutor() {
    }

    @Override
    public void update(Connection connection, String query) throws ExecutorException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new ExecutorException("Error with sql-connection or query-executing.", e);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of statement is failed.", e);
                }
            }
        }
    }

    @Override
    public <T> T select(Connection connection, String query, ResultHandler<T> resultHandler) throws ExecutorException {
        Statement statement = null;
        ResultSet resultSet = null;
        T result = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            result = resultHandler.handle(resultSet);
        } catch (SQLException e) {
            throw new ExecutorException("Error with sql-connection or query-executing.", e);
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of result-set is failed.", e);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of statement is failed.", e);
                }
            }
        }

        return result;
    }
}
