package by.training.zorich.controller;

public enum SessionAttribute {
    CURRENT_LOCALE("currentLocale"), CURRENT_USER_ID("currentUserId"), CURRENT_USER_NAME("userLogin"), CURRENT_USER_ROLE("userRole");

    final private String name;

    SessionAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public SessionAttribute getSessionAttributeByName(String name) {
        for (SessionAttribute item : SessionAttribute.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
