package maintask;

public class ParkingPlace {
    private int id;
    private boolean isEngaged;
    private Car engagedBy;
    private Parking parking;

    /**
     * Default constructor used in getAvailableParkingPlace() of Parking-class
     * to simulate case when no available parking place found at parking.
     */
    public ParkingPlace() {
        this.id = 0;
    }

    public ParkingPlace(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public void setEngaged(boolean engaged) {
        isEngaged = engaged;
    }

    public void setEngagedBy(Car engagedBy) {
        this.engagedBy = engagedBy;
    }

    public boolean isEngaged() {
        return isEngaged;
    }

    public void leaveParkingPlace() {
        System.out.println(engagedBy.toString() + " just leaved " + parking.getId() +
                " parking from " + id + " place");
        isEngaged = false;
    }

    public int getId() {
        return id;
    }
}
