package by.training.zorich.dal.connector.impl;

public enum MySqlDBNameParameter {
    DB_DRIVER("db.driver"),
    DB_URL("db.url"),
    DB_FILE("db.file"),
    DB_USER("db.user"),
    DB_PASSWORD("db.password"),
    DB_PARAMETER("db.parameter"),
    DB_POOL_SIZE("db.poolsize"),
    DB_GETTING_TIMEOUT("db.getting.timeout");

    final private String name;

    MySqlDBNameParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public MySqlDBNameParameter getSQLiteDBNameParameterByName(String name) {
        for (MySqlDBNameParameter item : MySqlDBNameParameter.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
