package by.training.zorich.controller;

public enum CookieName {
    CURRENT_LOCALE("currentLocale"), CURRENT_USER_ROLE("userRole");

    final private String name;

    CookieName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public CookieName getCookieName(String name) {
        for (CookieName item : CookieName.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
