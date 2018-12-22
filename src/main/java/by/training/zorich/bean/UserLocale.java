package by.training.zorich.bean;

public enum UserLocale {
    RU("ru_RU", 1), EN("en_EN", 2);

    final private String name;
    final private int idLocale;

    UserLocale(String name, int idLocale) {
        this.name = name;
        this.idLocale = idLocale;
    }

    public String getName() {
        return name;
    }

    public int getIdLocale() {
        return idLocale;
    }

    static public UserLocale getUserLocaleByName(String name) {
        for (UserLocale item : UserLocale.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
