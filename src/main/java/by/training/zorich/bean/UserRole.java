package by.training.zorich.bean;

public enum UserRole {
    ADMIN("администратор", 1), SUBSCRIBER("подписчик", 2), GUEST("гость", 3);

    final private String name;
    final private int idRole;

    UserRole(String name, int idRole) {
        this.name = name;
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public int getIdRole() {
        return idRole;
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
