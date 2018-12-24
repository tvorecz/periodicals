package by.training.zorich.dal.sql_executor.impl;

import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.PreparedStatementFiller;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.*;

public class MySqlExecutor implements SQLExecutor {
    public MySqlExecutor() {
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
            if (statement != null) {
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
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of result-set is failed.", e);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of statement is failed.", e);
                }
            }
        }

        return result;
    }

    @Override
    public void update(PreparedStatement preparedStatement, PreparedStatementFiller filler) throws ExecutorException {
        try {
            filler.fill(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecutorException("Error with query-executing.", e);
        }
    }

    @Override
    public <T> T select(PreparedStatement preparedStatement,
                        PreparedStatementFiller filler,
                        ResultHandler<T> resultHandler) throws ExecutorException {
        ResultSet resultSet = null;
        T result = null;

        try {
            filler.fill(preparedStatement);

            resultSet = preparedStatement.executeQuery();

            result = resultHandler.handle(resultSet);
        } catch (SQLException e) {
            throw new ExecutorException("Error with query-executing.", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new ExecutorException("Closing of result-set is failed.", e);
                }
            }
        }

        return result;
    }
}
