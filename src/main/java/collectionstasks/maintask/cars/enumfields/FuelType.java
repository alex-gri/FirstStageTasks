package collectionstasks.maintask.cars.enumfields;

public enum FuelType {
    DIESEL("Дизель"),
    GAS("Бензин"),
    HYBRID("Гибрид"),
    ELECTRO("Электрокар");
    public String fuelTypeName;

    FuelType(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName;
    }

}
