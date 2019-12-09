package maintask;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Car implements Runnable {
    static Logger logger = Logger.getLogger(Car.class.getName());
    static Random random = new Random();

    private BlockingQueue<Integer> parkingPlaces;
    private int id;

    // Number of place where car is parked.
    private int parkingPlaceTaken;

    public Car(int id, BlockingQueue<Integer> parkingPlaces) {
        this.id = id;
        this.parkingPlaces = parkingPlaces;
    }

    @Override
    public void run() {
        try {
            park();
            TimeUnit.SECONDS.sleep(random.nextInt(3));
            if (parkingPlaceTaken != 0) {
                unpark();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void park() throws InterruptedException {
        parkingPlaceTaken = parkingPlaces.take();
        System.out.println(toString() + " parked at " + parkingPlaceTaken);
    }

    public void unpark() throws InterruptedException {
        parkingPlaces.put(parkingPlaceTaken);
        System.out.println(toString() + " just leaved the parking place " + parkingPlaceTaken);
        parkingPlaceTaken = 0;
    }

    @Override
    public String toString() {
        return "Car № " + id;
    }
}
