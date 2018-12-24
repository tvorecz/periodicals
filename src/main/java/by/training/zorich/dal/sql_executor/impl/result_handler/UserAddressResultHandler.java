package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.UserAddress;
import by.training.zorich.bean.UserAddressCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressResultHandler implements ResultHandler<List<UserAddress>> {

    @Override
    public List<UserAddress> handle(ResultSet resultSet) throws SQLException {
        List<UserAddress> result = new ArrayList<>();

        while (resultSet.next()) {
            UserAddress userAddress = new UserAddress();

            userAddress.setIdAdress(resultSet.getInt(UserAddressCharacteristic.ID.getName()));
            userAddress.setIdUser(resultSet.getInt(UserAddressCharacteristic.ID_USER.getName()));
            userAddress.setAddress(resultSet.getString(UserAddressCharacteristic.ADDRESS.getName()));

            result.add(userAddress);
        }

        return result;
    }
}
