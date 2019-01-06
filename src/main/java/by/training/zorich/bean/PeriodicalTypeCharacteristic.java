package by.training.zorich.bean;

public enum PeriodicalTypeCharacteristic {
    ID("idType"),
    NAME("typeName");

    final private String name;

    PeriodicalTypeCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public PeriodicalTypeCharacteristic getPeriodicalTypeCharacteristicByName(String name) {
        for (PeriodicalTypeCharacteristic item : PeriodicalTypeCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
