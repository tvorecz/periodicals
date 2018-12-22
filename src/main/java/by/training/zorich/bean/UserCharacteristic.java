package by.training.zorich.bean;

public enum UserCharacteristic {
    ID("idUser"),
    LOGIN("login"),
    CODIFIED_PASSWORD("password"),
    REAL_PASSWORD("password"),
    EMAIL("email"),
    ID_ROLE("idRole"),
    NAME_ROLE("nameRole"),
    LOCALE("nameLocal");

    final private String name;

    UserCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public UserCharacteristic getUserParameterByName(String name) {
        for (UserCharacteristic item : UserCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
