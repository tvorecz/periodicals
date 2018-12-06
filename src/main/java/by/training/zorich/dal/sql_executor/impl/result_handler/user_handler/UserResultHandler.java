package by.training.zorich.dal.sql_executor.impl.result_handler.user_handler;

import by.training.zorich.bean.User;
import by.training.zorich.controller.const_parameter.UserCharacteristic;
import by.training.zorich.controller.const_parameter.UserRole;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultHandler implements ResultHandler<User> {

    @Override
    public User handle(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            User foundUser = new User();
            foundUser.setId(resultSet.getInt(UserCharacteristic.ID.getName()));
            foundUser.setLogin(resultSet.getString(UserCharacteristic.LOGIN.getName()));
            foundUser.setEmail(resultSet.getString(UserCharacteristic.EMAIL.getName()));
            foundUser.setCodifiedPassword(resultSet.getString(UserCharacteristic.CODIFIED_PASSWORD.getName()));

            String userRole = resultSet.getString(UserCharacteristic.NAME_ROLE.getName());
            foundUser.setRole(UserRole.getUserRoleByName(userRole));

            return foundUser;
        }

        return null;
    }
}
