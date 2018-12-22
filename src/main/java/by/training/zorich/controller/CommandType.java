package by.training.zorich.controller;

public enum CommandType {
    COMMAND("command"),
    LOGIN("login"),
    REGISTER("register"),
    LOCALE("changeLocale"),
    ;

    final private String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public CommandType getActionParameterByName(String name) {
        for (CommandType item : CommandType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
