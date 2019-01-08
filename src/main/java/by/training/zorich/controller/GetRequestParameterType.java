package by.training.zorich.controller;

public enum GetRequestParameterType {
    RETURN("return"),
    LOCALE("locale"),
    ITEM("item"),
    ;

    final private String name;

    GetRequestParameterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public HandlerType getGetRequestParameterTypeByName(String name) {
        for (HandlerType item : HandlerType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
