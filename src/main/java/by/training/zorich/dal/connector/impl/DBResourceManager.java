package by.training.zorich.dal.connector.impl;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("/property/database");

    private DBResourceManager() {
    }

    private static class DBResourceManagerHelper {
        private static final DBResourceManager MANAGER = new DBResourceManager();
    }

    public static DBResourceManager getInstance() {
        return DBResourceManagerHelper.MANAGER;
    }

    public String getResourceValue(String key) {
        return resourceBundle.getString(key);
    }
}
