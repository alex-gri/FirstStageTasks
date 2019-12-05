package maintask;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Parking {
    static Logger logger = Logger.getLogger(Parking.class.getName());
    // Number of cars that can park at one parking.
    public static final int LIMIT_OF_CARS = 3;
    private int id;

    // Number of each parking place inside of parking.
    private AtomicInteger parkingPlacesUsed;

    /**
     * Default constructor used in getAvailableParkingPlace() of Car-class
     * to simulate case when no available parking place found.
     */
    public Parking() {
        this.id = 0;
        this.parkingPlacesUsed = new AtomicInteger(0);
    }

    public Parking(int id) {
        this.id = id;
        this.parkingPlacesUsed = new AtomicInteger(0);
    }

    // Checks if there is any car parked and decrements number of used parking places when one car leaves.
    public void releaseParkingPlace() {
        if (parkingPlacesUsed.intValue() > 0) {
            parkingPlacesUsed.decrementAndGet();
        } else {
            logger.severe("Nothing to release");
        }
    }

    // Checks if there is any available parking place and increments number of used parking places when car parks.
    public void engageParkingPlace() {
        if (hasParkingPlaceAvailable()) {
            parkingPlacesUsed.incrementAndGet();
        } else {
            logger.severe("No parking places available");
        }
    }

    public int getParkingPlacesUsed() {
        return parkingPlacesUsed.intValue();
    }

    public boolean hasParkingPlaceAvailable() {
        return (LIMIT_OF_CARS - parkingPlacesUsed.intValue()) > 0;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Parking №" + id + ", parkingPlacesLeft = " + (LIMIT_OF_CARS - parkingPlacesUsed.get());
    }
}
