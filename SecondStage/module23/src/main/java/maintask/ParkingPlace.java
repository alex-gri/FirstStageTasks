package maintask;

public class ParkingPlace {
    private int id;
    private boolean isEmpty;

    public ParkingPlace() {
        this.id = 0;
        this.isEmpty = true;
    }

    public ParkingPlace(int indexNumber) {
        this.id = indexNumber;
        this.isEmpty = true;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
        return "ParkingPlace №" + id +": isEmpty = " + isEmpty;
    }
}
