package by.training.zorich.bean;

public enum SubscriptionVariantCharacteristic {
    ID("idSubscriptionVariant"),
    INDEX("indexSubscription"),
    COST("costForIssue");

    final private String name;

    SubscriptionVariantCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public SubscriptionVariantCharacteristic getSubscriptionVariantCharacteristicByName(String name) {
        for (SubscriptionVariantCharacteristic item : SubscriptionVariantCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
