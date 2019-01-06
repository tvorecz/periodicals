package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.PeriodicalType;
import by.training.zorich.bean.PeriodicalTypeCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalTypeResultHandler implements ResultHandler<List<PeriodicalType>> {

    @Override
    public List<PeriodicalType> handle(ResultSet resultSet) throws SQLException {
        List<PeriodicalType> result = new ArrayList<>();

        while (resultSet.next()) {
            PeriodicalType periodicalType = new PeriodicalType();

            periodicalType.setId(resultSet.getInt(PeriodicalTypeCharacteristic.ID.getName()));
            periodicalType.setName(resultSet.getString(PeriodicalTypeCharacteristic.NAME.getName()));

            result.add(periodicalType);
        }

        return result;
    }
}
