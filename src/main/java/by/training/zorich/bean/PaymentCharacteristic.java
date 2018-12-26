package by.training.zorich.bean;

public enum PaymentCharacteristic {
    ID("idPayment"),
    AMOUNT("amount"),
    PAY_STATUS("payStatus");

    final private String name;

    PaymentCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public PaymentCharacteristic getPaymentCharacteristicByName(String name) {
        for (PaymentCharacteristic item : PaymentCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
