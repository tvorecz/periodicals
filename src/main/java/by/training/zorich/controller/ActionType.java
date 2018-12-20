package by.training.zorich.controller;

public enum ActionType {
    COMMAND("command"), SIGN_IN("/SignIn"), SIGN_UP("/SignUp"), ;

    final private String name;

    ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public ActionType getActionParameterByName(String name) {
        for (ActionType item : ActionType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
