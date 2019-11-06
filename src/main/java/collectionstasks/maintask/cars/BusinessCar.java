package collectionstasks.maintask.cars;

import collectionstasks.maintask.cars.enumfields.FuelType;
import collectionstasks.maintask.cars.enumfields.Manufacturer;

import java.util.Objects;

public class BusinessCar extends ComfortCar {
    private int numberOfPhonesInCar;
    private int displaysDiagonal;
    private String windowsTintingLevel;
    private boolean hasSeatAirCirculation;

    public BusinessCar(String id, Manufacturer manufacturer, String model, int yearOfManufacture,
                       FuelType fuelType, double fuelConsumption, int fuelReserve,
                       int sitPlacesNumber, int cost, int maxSpeed, String suspensionHardness,
                       String gearboxRobotType, boolean hasSeatHeater, int climateZonesNumber,
                       int numberOfPhonesInCar, int displaysDiagonal, String windowsTintingLevel,
                       boolean hasSeatAirCirculation) {
        super(id, manufacturer, model, yearOfManufacture, fuelType,
                fuelConsumption, fuelReserve, sitPlacesNumber,
                cost, maxSpeed, suspensionHardness, gearboxRobotType,
                hasSeatHeater, climateZonesNumber);
        this.numberOfPhonesInCar = numberOfPhonesInCar;
        this.displaysDiagonal = displaysDiagonal;
        this.windowsTintingLevel = windowsTintingLevel;
        this.hasSeatAirCirculation = hasSeatAirCirculation;

    }

    public int getNumberOfPhonesInCar() {
        return numberOfPhonesInCar;
    }

    public void setNumberOfPhonesInCar(int numberOfPhonesInCar) {
        this.numberOfPhonesInCar = numberOfPhonesInCar;
    }

    public int getDisplaysDiagonal() {
        return displaysDiagonal;
    }

    public void setDisplaysDiagonal(int displaysDiagonal) {
        this.displaysDiagonal = displaysDiagonal;
    }

    public String getWindowsTintingLevel() {
        return windowsTintingLevel;
    }

    public void setWindowsTintingLevel(String windowsTintingLevel) {
        this.windowsTintingLevel = windowsTintingLevel;
    }

    public boolean isHasSeatAirCirculation() {
        return hasSeatAirCirculation;
    }

    public void setHasSeatAirCirculation(boolean hasSeatAirCirculation) {
        this.hasSeatAirCirculation = hasSeatAirCirculation;
    }

    @Override
    public String toString() {
        return super.toString().replace("Комфорт-класс. ", "Бизнес-класс. ") +
                "Количество телефонов=" + numberOfPhonesInCar +
                ", Диагональ экранов=" + displaysDiagonal +
                ", Тип тонировки стекол='" + windowsTintingLevel + '\'' +
                ", Вентиляция сидений=" + hasSeatAirCirculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BusinessCar that = (BusinessCar) o;
        return numberOfPhonesInCar == that.numberOfPhonesInCar &&
                displaysDiagonal == that.displaysDiagonal &&
                hasSeatAirCirculation == that.hasSeatAirCirculation &&
                Objects.equals(windowsTintingLevel, that.windowsTintingLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPhonesInCar, displaysDiagonal, windowsTintingLevel, hasSeatAirCirculation);
    }
}
