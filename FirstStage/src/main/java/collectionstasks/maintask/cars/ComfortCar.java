package collectionstasks.maintask.cars;

import collectionstasks.maintask.cars.enumfields.FuelType;
import collectionstasks.maintask.cars.enumfields.Manufacturer;

import java.util.Objects;

public class ComfortCar extends Car {
    private String suspensionHardness;
    private String gearboxRobotType;
    private boolean hasSeatHeater;
    private int climateZonesNumber;

    public ComfortCar(String id, Manufacturer manufacturer, String model, int yearOfManufacture,
                      FuelType fuelType, double fuelConsumption, int fuelReserve,
                      int sitPlacesNumber, int cost, int maxSpeed,
                      String suspensionHardness, String gearboxRobotType,
                      boolean hasSeatHeater, int climateZonesNumber) {
        super(id, manufacturer, model, yearOfManufacture,
                fuelType, fuelConsumption, fuelReserve,
                sitPlacesNumber, cost, maxSpeed);
        this.suspensionHardness = suspensionHardness;
        this.gearboxRobotType = gearboxRobotType;
        this.hasSeatHeater = hasSeatHeater;
        this.climateZonesNumber = climateZonesNumber;
    }

    public String getSuspensionHardness() {
        return suspensionHardness;
    }

    public String getGearboxRobotType() {
        return gearboxRobotType;
    }

    public boolean isHasSeatHeater() {
        return hasSeatHeater;
    }

    public int getClimateZonesNumber() {
        return climateZonesNumber;
    }

    public void setSuspensionHardness(String suspensionHardness) {
        this.suspensionHardness = suspensionHardness;
    }

    public void setGearboxRobotType(String gearboxRobotType) {
        this.gearboxRobotType = gearboxRobotType;
    }

    public void setHasSeatHeater(boolean hasSeatHeater) {
        this.hasSeatHeater = hasSeatHeater;
    }

    public void setClimateZonesNumber(int climateZonesNumber) {
        this.climateZonesNumber = climateZonesNumber;
    }

    @Override
    public String toString() {
        return "Комфорт-класс. " + super.toString() +
                ", Жесткость подвески='" + suspensionHardness + '\'' +
                ", Тип АКП='" + gearboxRobotType + '\'' +
                ", Подогрев сидений=" + hasSeatHeater +
                ", Количество зон климат-контроля=" + climateZonesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComfortCar that = (ComfortCar) o;
        return hasSeatHeater == that.hasSeatHeater &&
                climateZonesNumber == that.climateZonesNumber &&
                Objects.equals(suspensionHardness, that.suspensionHardness) &&
                Objects.equals(gearboxRobotType, that.gearboxRobotType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), suspensionHardness, gearboxRobotType, hasSeatHeater, climateZonesNumber);
    }
}
