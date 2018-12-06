package by.training.zorich.controller.const_parameter;

public enum UserRole {
    ADMIN("администратор"), SUBSCRIBER("подписчик");

    final private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public UserRole getUserRoleByName(String name) {
        for (UserRole item : UserRole.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
