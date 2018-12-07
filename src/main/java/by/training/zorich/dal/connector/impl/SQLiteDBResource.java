package by.training.zorich.dal.connector.impl;

import java.util.ListResourceBundle;

public class SQLiteDBResource extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{{"db.driver", "org.sqlite.JDBC"}, {"db.url", "jdbc:sqlite:C://Users/tvore/Documents" +
                                                                           "/EpamJavaWebDevelopment/Periodicals/target/zorich.periodicals/WEB-INF/classes/Periodicals.db"}, {"db.user",
                ""}, {"db.password", ""}, {"db.poolsize", "5"}, {"db.getting.timeout", "3000"}};
    }
}
