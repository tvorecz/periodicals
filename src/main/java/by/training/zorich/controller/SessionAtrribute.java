package by.training.zorich.controller;

public enum SessionAtrribute {
    CURRENT_LOCALE("currentLocale"), CURRENT_USER_ID("currentUserId"), CURRENT_USER_NAME("userLogin"), CURRENT_USER_ROLE("userRole");

    final private String name;

    SessionAtrribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public ActionType getSessionAtrributeByName(String name) {
        for (ActionType item : ActionType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
