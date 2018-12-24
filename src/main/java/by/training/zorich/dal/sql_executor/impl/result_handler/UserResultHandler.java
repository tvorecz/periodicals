package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.User;
import by.training.zorich.bean.UserCharacteristic;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.bean.UserRole;
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

            String userRole = resultSet.getString(UserCharacteristic.NAME_ROLE.getName());
            foundUser.setRole(UserRole.getUserRoleByName(userRole));

            String userLocale = resultSet.getString(UserCharacteristic.LOCALE.getName());
            foundUser.setCurrentLocale(UserLocale.getUserLocaleByName(userLocale));

            return foundUser;
        }

        return null;
    }
}
