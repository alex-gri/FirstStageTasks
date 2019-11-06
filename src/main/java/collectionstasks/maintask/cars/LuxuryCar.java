package collectionstasks.maintask.cars;

import collectionstasks.maintask.cars.enumfields.FuelType;
import collectionstasks.maintask.cars.enumfields.Manufacturer;

import java.util.Objects;

public class LuxuryCar extends BusinessCar {
    private String alcoholInBarIncluded;
    private boolean hasWallBetweenBackAndFrontSeats;
    private String interiorLeatherType;
    private String audioSystemDescription;

    public LuxuryCar(String id, Manufacturer manufacturer, String model, int yearOfManufacture,
                     FuelType fuelType, double fuelConsumption, int fuelReserve,
                     int sitPlacesNumber, int cost, int maxSpeed,
                     String suspensionHardness, String gearboxRobotType, boolean hasSeatHeater,
                     int climateZonesNumber, int numberOfPhonesInCar, int displaysDiagonal,
                     String windowsTintingLevel, boolean hasSeatAirCirculation,
                     String alcoholInBarIncluded, boolean hasWallBetweenBackAndFrontSeats,
                     String interiorLeatherType, String audioSystemDescription) {
        super(id, manufacturer, model, yearOfManufacture, fuelType, fuelConsumption, fuelReserve, sitPlacesNumber, cost, maxSpeed, suspensionHardness, gearboxRobotType, hasSeatHeater, climateZonesNumber, numberOfPhonesInCar, displaysDiagonal, windowsTintingLevel, hasSeatAirCirculation);
        this.alcoholInBarIncluded = alcoholInBarIncluded;
        this.hasWallBetweenBackAndFrontSeats = hasWallBetweenBackAndFrontSeats;
        this.interiorLeatherType = interiorLeatherType;
        this.audioSystemDescription = audioSystemDescription;
    }

    public String getAlcoholInBarIncluded() {
        return alcoholInBarIncluded;
    }

    public void setAlcoholInBarIncluded(String alcoholInBarIncluded) {
        this.alcoholInBarIncluded = alcoholInBarIncluded;
    }

    public boolean isHasWallBetweenBackAndFrontSeats() {
        return hasWallBetweenBackAndFrontSeats;
    }

    public void setHasWallBetweenBackAndFrontSeats(boolean hasWallBetweenBackAndFrontSeats) {
        this.hasWallBetweenBackAndFrontSeats = hasWallBetweenBackAndFrontSeats;
    }

    public String getInteriorLeatherType() {
        return interiorLeatherType;
    }

    public void setInteriorLeatherType(String interiorLeatherType) {
        this.interiorLeatherType = interiorLeatherType;
    }

    public String getAudioSystemDescription() {
        return audioSystemDescription;
    }

    public void setAudioSystemDescription(String audioSystemDescription) {
        this.audioSystemDescription = audioSystemDescription;
    }

    @Override
    public String toString() {
        return super.toString().replace("Бизнес-класс. ", "Премиум-класс. ") +
                "Алкоголь в баре='" + alcoholInBarIncluded + '\'' +
                ", Приватная задняя зона=" + hasWallBetweenBackAndFrontSeats +
                ", Тип кожи в салоне='" + interiorLeatherType + '\'' +
                ", Аудиосистема='" + audioSystemDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LuxuryCar luxuryCar = (LuxuryCar) o;
        return hasWallBetweenBackAndFrontSeats == luxuryCar.hasWallBetweenBackAndFrontSeats &&
                Objects.equals(alcoholInBarIncluded, luxuryCar.alcoholInBarIncluded) &&
                Objects.equals(interiorLeatherType, luxuryCar.interiorLeatherType) &&
                Objects.equals(audioSystemDescription, luxuryCar.audioSystemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alcoholInBarIncluded, hasWallBetweenBackAndFrontSeats, interiorLeatherType, audioSystemDescription);
    }
}
