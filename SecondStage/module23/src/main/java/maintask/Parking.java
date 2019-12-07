package maintask;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private int id;
    private List<ParkingPlace> parkingPlaces;

    /**
     * Default constructor used in getAvailableParking() of Car-class to simulate case
     * when no available parking found (all places are engaged).
     */
    public Parking() {
        this.id = 0;
        initializeParkingPlaces();
    }

    public Parking(int id) {
        this.id = id;
        initializeParkingPlaces();
    }

    public void initializeParkingPlaces() {
        ArrayList<ParkingPlace> newParkingPlaces = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            newParkingPlaces.add(new ParkingPlace(i+1, this));
        }
        this.parkingPlaces = newParkingPlaces;
    }

    public boolean hasParkingPlaceAvailable() {
        boolean hasParkingPlaceAvailable = false;
        for (ParkingPlace parkingPlace: parkingPlaces) {
            if (!parkingPlace.isEngaged()) {
                hasParkingPlaceAvailable = true;
                break;
            }
        }
        return hasParkingPlaceAvailable;
    }

    public ParkingPlace getAvailableParkingPlace() {
        ParkingPlace availableParkingPlace = new ParkingPlace();
        for (ParkingPlace parkingPlace: parkingPlaces) {
            if (!parkingPlace.isEngaged()) {
                availableParkingPlace = parkingPlace;
                break;
            }
        }
        return availableParkingPlace;
    }

    public int getId() {
        return id;
    }
}
