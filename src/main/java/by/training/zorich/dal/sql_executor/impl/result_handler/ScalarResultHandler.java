package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScalarResultHandler implements ResultHandler<Integer> {

    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        Integer result = null;

        if(resultSet.next()) {
            result = resultSet.getInt(1);
        }

        return result;
    }
}
