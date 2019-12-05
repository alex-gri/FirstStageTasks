package maintask;


import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Car implements Runnable {
    static Logger logger = Logger.getLogger(Car.class.getName());

    private final BlockingQueue<Car> parkedCars;
    private int id;

    // Number of parking place where car is parked.
    private int parkingPlace;

    // Parking at which car is parked.
    private Parking parkedAt;

    public Car(int id, BlockingQueue<Car> parkedCars) {
        this.parkedCars = parkedCars;
        this.id = id;
        this.parkingPlace = 0;
    }

    @Override
    public void run() {
        try {
            park();
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /*
     *  Checks if there is any parking place available and parks a car there
     *  remembering number of parking and parking place.
     *  Also blocking the car adding it to the BlockingQueue.
     */
    public void park() throws InterruptedException {
        parkedAt = getAvailableParkingPlace();

        // Waits until there is available parking place.
        while (parkedAt.getId() == 0) {
            Thread.sleep(100);
            parkedAt = getAvailableParkingPlace();
        }

        // Remembers number of parking place.
        parkingPlace = parkedAt.getParkingPlacesUsed() + 1;

        // Increments number of parking places used at parking.
        parkedAt.engageParkingPlace();
        parkedCars.put(this);
        System.out.println(toString() + " parked at " + parkedAt.getId() + " parking at " + parkingPlace + " place");
    }

    //
    public void leaveParkingPlace() {
        System.out.println(toString() + " just leaved " + parkedAt.getId() +
                " parking from " + parkingPlace + " place");
        parkedAt.releaseParkingPlace();
    }

    public Parking getAvailableParkingPlace() {
        Parking placeToPark = new Parking();
        for (Parking parking: Runner.parkings) {
            if (parking.hasParkingPlaceAvailable()) {
                placeToPark = parking;
                break;
            }
        }
        return placeToPark;
    }

    @Override
    public String toString() {
        return "Car № " + id;
    }
}
