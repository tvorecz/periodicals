package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdUserRoleResultHandler implements ResultHandler<Integer> {
    private final static String ID_ROLE = "idRole";

    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt(ID_ROLE);
        }

        return null;
    }
}
