package by.training.zorich.bean;

public enum PeriodicalThemeCharacteristic {
    ID("idTheme"),
    NAME("nameTheme");

    final private String name;

    PeriodicalThemeCharacteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public PeriodicalThemeCharacteristic getPeriodicalThemeCharacteristicByName(String name) {
        for (PeriodicalThemeCharacteristic item : PeriodicalThemeCharacteristic.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
