package by.training.zorich.dal.sql_executor;

import by.training.zorich.dal.exception.ExecutorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public interface SQLExecutor {
    void update(Connection connection, String query) throws ExecutorException;

    void update(PreparedStatement preparedStatement, PreparedStatementFiller filler) throws ExecutorException;

    <T> T select(Connection connection, String query, ResultHandler<T> resultHandler) throws ExecutorException;

    <T> T select(PreparedStatement preparedStatement, PreparedStatementFiller filler, ResultHandler<T> resultHandler) throws ExecutorException;
}
