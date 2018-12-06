package by.training.zorich.dal.sql_executor;

import by.training.zorich.dal.exception.ExecutorException;

import java.sql.Connection;

public interface Executor {
    void update(Connection connection, String query) throws ExecutorException;

    <T> T select(Connection connection, String query, ResultHandler<T> resultHandler) throws ExecutorException;
}
