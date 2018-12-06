package by.training.zorich.dal.connector.impl;

public enum SQLiteDBNameParameter {
    DB_DRIVER("db.driver"),
    DB_URL("db.url"),
    DB_USER("db.user"),
    DB_PASSWORD("db.password"),
    DB_POOL_SIZE("db.poolsize"),
    DB_GETTING_TIMEOUT("db.getting.timeout");

    final private String name;

    SQLiteDBNameParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public SQLiteDBNameParameter getSQLiteDBNameParameterByName(String name) {
        for (SQLiteDBNameParameter item : SQLiteDBNameParameter.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
