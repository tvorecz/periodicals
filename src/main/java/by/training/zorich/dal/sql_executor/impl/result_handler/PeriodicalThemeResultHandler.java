package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.PeriodicalTheme;
import by.training.zorich.bean.PeriodicalThemeCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalThemeResultHandler implements ResultHandler<List<PeriodicalTheme>> {
    @Override
    public List<PeriodicalTheme> handle(ResultSet resultSet) throws SQLException {
        List<PeriodicalTheme> result = new ArrayList<>();

        while (resultSet.next()) {
            PeriodicalTheme periodicalTheme = new PeriodicalTheme();

            periodicalTheme.setId(resultSet.getInt(PeriodicalThemeCharacteristic.ID.getName()));
            periodicalTheme.setName(resultSet.getString(PeriodicalThemeCharacteristic.NAME.getName()));

            result.add(periodicalTheme);
        }

        return result;
    }
}
