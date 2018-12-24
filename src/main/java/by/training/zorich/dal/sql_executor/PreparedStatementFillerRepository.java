package by.training.zorich.dal.sql_executor;

public interface PreparedStatementFillerRepository {
    PreparedStatementFiller getFiller(PreparedStatementFillerType type);
}
