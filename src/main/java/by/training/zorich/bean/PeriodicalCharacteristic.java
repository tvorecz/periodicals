package by.training.zorich.bean;

public enum PeriodicalCharacteristic {
    ID("idPeriodical"),
    TYPE("typeName"),
    THEME("nameTheme"),
    NAME("namePeriodical"),
    PERIODICITY("periodicityInMonth"),
    ANNOTATION("annotation"),
    IMAGE("imagePath");

    final private String name;

    PeriodicalCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public PeriodicalCharacteristic getPeriodicalCharacteristicByName(String name) {
        for (PeriodicalCharacteristic item : PeriodicalCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
