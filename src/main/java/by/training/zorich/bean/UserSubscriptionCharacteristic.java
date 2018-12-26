package by.training.zorich.bean;

public enum UserSubscriptionCharacteristic {
    ID("idUserSubscription"),
    DATE_BEGIN("dateBegin"),
    DATE_END("dateEnd");

    final private String name;

    UserSubscriptionCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public UserSubscriptionCharacteristic getUserSubscriptionCharacteristicByName(String name) {
        for (UserSubscriptionCharacteristic item : UserSubscriptionCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
