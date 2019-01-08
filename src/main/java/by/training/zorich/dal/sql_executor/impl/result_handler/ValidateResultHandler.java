package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateResultHandler implements ResultHandler<Boolean> {

    @Override
    public Boolean handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return false;
        }

        return true;
    }
}