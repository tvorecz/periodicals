package by.training.zorich.bean;

public enum UserAddressCharacteristic {
    ID("idAddress"),
    ADDRESS("address"),
    ID_USER("idUser");

    final private String name;

    UserAddressCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public UserAddressCharacteristic getUserAddressCharacteristicByName(String name) {
        for (UserAddressCharacteristic item : UserAddressCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
