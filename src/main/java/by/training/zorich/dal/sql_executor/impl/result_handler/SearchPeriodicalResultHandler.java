package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.*;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchPeriodicalResultHandler implements ResultHandler<List<Periodical>> {

    @Override
    public List<Periodical> handle(ResultSet resultSet) throws SQLException {
        List<Periodical> periodicalList = new ArrayList<>();

        while (resultSet.next()) {
            Periodical periodical = periodical = new Periodical();
            periodical.setId(resultSet.getInt(PeriodicalCharacteristic.ID.getName()));
            periodical.setName(resultSet.getString(PeriodicalCharacteristic.NAME.getName()));
            periodical.setAnnotation(resultSet.getString(PeriodicalCharacteristic.ANNOTATION.getName()));
            periodical.setImagePath(resultSet.getString(PeriodicalCharacteristic.IMAGE.getName()));
            periodical.setPeriodicityInMonth(resultSet.getInt(PeriodicalCharacteristic.PERIODICITY.getName()));

            PeriodicalType periodicalType = new PeriodicalType();
            periodicalType.setId(resultSet.getInt(PeriodicalTypeCharacteristic.ID.getName()));
            periodicalType.setName(resultSet.getString(PeriodicalTypeCharacteristic.NAME.getName()));
            periodical.setType(periodicalType);

            PeriodicalTheme periodicalTheme = new PeriodicalTheme();
            periodicalTheme.setId(resultSet.getInt(PeriodicalThemeCharacteristic.ID.getName()));
            periodicalTheme.setName(resultSet.getString(PeriodicalThemeCharacteristic.NAME.getName()));
            periodical.setTheme(periodicalTheme);

            periodicalList.add(periodical);
        }

        return periodicalList;
    }
}
