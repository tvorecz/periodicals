package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LastIdResultHandler implements ResultHandler<Integer> {

    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        Integer result = resultSet.getInt(0);

        return result;
    }
}
