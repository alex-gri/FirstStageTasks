package maintask;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Car implements Runnable {
    static Logger logger = Logger.getLogger(Car.class.getName());

    private final BlockingQueue<Car> parkedCars;
    private final BlockingQueue<ParkingPlace> engagedParkingPlace;
    private int id;

    public BlockingQueue<ParkingPlace> getEngagedParkingPlace() {
        return engagedParkingPlace;
    }

    public Car(int id, BlockingQueue<Car> parkedCars) {
        this.parkedCars = parkedCars;
        this.id = id;
        this.engagedParkingPlace = new ArrayBlockingQueue<>(10);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(75);
            park();
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void park() throws InterruptedException {
        Parking parkedAt = getAvailableParking();
        ParkingPlace parkingPlace = parkedAt.getAvailableParkingPlace();
        parkingPlace.setEngaged(true);
        parkingPlace.setEngagedBy(this);
        parkedCars.put(this);
        engagedParkingPlace.put(parkingPlace);
        System.out.println(toString() + " parked at " + parkedAt.getId() + " parking at " + parkingPlace.getId() + " place");
    }

    public Parking getAvailableParking() {
        Parking availableParking = new Parking();
        for (Parking parking : Runner.parkings) {
            if (parking.hasParkingPlaceAvailable()) {
                availableParking = parking;
                break;
            }
        }
        return availableParking;
    }

    @Override
    public String toString() {
        return "Car № " + id;
    }
}
