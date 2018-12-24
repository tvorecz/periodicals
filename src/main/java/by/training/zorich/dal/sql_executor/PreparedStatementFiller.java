package by.training.zorich.dal.sql_executor;

import java.sql.PreparedStatement;

public interface PreparedStatementFiller {
    void fill(PreparedStatement preparedStatement);
}
