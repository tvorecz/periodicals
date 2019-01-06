package by.training.zorich.controller;

public enum HandlerType {
    COMMAND("command"),
    LOGIN("login"),
    REGISTER("register"),
    LOCALE("changeLocale"),
    RETURN_PAGE("target"),
    URI_ADD_PERIODICAL("/admin/added"),
    PREPROCESS_ADD("/admin/add"),
    PREPROCESS_ADDED("/admin/added"),
    PREPROCESS_EDIT("/admin/edit"),
    PREPROCESS_EDITED("admin/edited"),

    ;

    final private String name;

    HandlerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public HandlerType getActionParameterByName(String name) {
        for (HandlerType item : HandlerType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
