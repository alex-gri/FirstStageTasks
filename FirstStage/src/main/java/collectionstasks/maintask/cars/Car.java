package collectionstasks.maintask.cars;

import collectionstasks.maintask.cars.enumfields.FuelType;
import collectionstasks.maintask.cars.enumfields.Manufacturer;

import java.util.Objects;

abstract public class Car {
    private String id;
    private Manufacturer manufacturer;
    private String model;
    private int yearOfManufacture;
    private FuelType fuelType;
    private double fuelConsumption;
    private int fuelReserve;
    private int sitPlacesNumber;
    private int cost;
    private int maxSpeed;

    public Car(String id, Manufacturer manufacturer, String model,
               int yearOfManufacture, FuelType fuelType, double fuelConsumption,
               int fuelReserve, int sitPlacesNumber, int cost, int maxSpeed) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.fuelReserve = fuelReserve;
        this.sitPlacesNumber = sitPlacesNumber;
        this.cost = cost;
        this.maxSpeed = maxSpeed;
    }

    public String getId() {
        return id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getFuelReserve() {
        return fuelReserve;
    }

    public int getSitPlacesNumber() {
        return sitPlacesNumber;
    }

    public int getCost() {
        return cost;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return  "Р/н='" + id + '\'' +
                ", Марка=" + manufacturer.manufacturerName +
                ", Модель='" + model + '\'' +
                ", Год выпуска='" + yearOfManufacture + '\'' +
                ", Тип топлива=" + fuelType.fuelTypeName +
                ", Расход=" + fuelConsumption +
                ", Запас топлива=" + fuelReserve +
                ", Количество сидений=" + sitPlacesNumber +
                ", Стоимость автомобиля=" + cost + " $" +
                ", Максимальная скорость=" + maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfManufacture == car.yearOfManufacture &&
                Double.compare(car.fuelConsumption, fuelConsumption) == 0 &&
                fuelReserve == car.fuelReserve &&
                sitPlacesNumber == car.sitPlacesNumber &&
                cost == car.cost &&
                maxSpeed == car.maxSpeed &&
                Objects.equals(id, car.id) &&
                manufacturer == car.manufacturer &&
                Objects.equals(model, car.model) &&
                fuelType == car.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, yearOfManufacture, fuelType, fuelConsumption, fuelReserve, sitPlacesNumber, cost, maxSpeed);
    }
}