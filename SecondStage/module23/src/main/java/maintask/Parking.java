package maintask;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private int id;
    private List<ParkingPlace> parkingPlaces;

    public Parking(int id) {
        this.id = id;
        this.parkingPlaces = initializeParkingPlaces();
    }

    public int getId() {
        return id;
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    private static ArrayList<ParkingPlace> initializeParkingPlaces() {
        ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            parkingPlaces.add(new ParkingPlace(i + 1));
        }
        return parkingPlaces;
    }

    @Override
    public String toString() {
        return "Parking №" + id + ", parkingPlaces = " + parkingPlaces;
    }
}
