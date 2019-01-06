package by.training.zorich.bean;

public enum SubscriptionTypeCharacteristic {
    ID("idSubscriptionType"),
    NAME("nameSubscriptionType"),
    MONTH_AMOUNT("monthAmount");

    final private String name;

    SubscriptionTypeCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public SubscriptionTypeCharacteristic getSubscriptionTypeCharacteristicByName(String name) {
        for (SubscriptionTypeCharacteristic item : SubscriptionTypeCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
