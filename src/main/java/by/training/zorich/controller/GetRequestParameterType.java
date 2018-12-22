package by.training.zorich.controller;

public enum GetRequestParameterType {
    RETURN("return"),
    LOCALE("locale"),
    ;

    final private String name;

    GetRequestParameterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public CommandType getGetRequestParameterTypeByName(String name) {
        for (CommandType item : CommandType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
