package maintask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    static Logger logger = LogManager.getLogger();
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
        StringBuilder stringBuilder = new StringBuilder();
        parkingPlaceTaken = parkingPlaces.take();
        logger.info(stringBuilder.append(toString()).append(" parked at ").
                    append(parkingPlaceTaken).append(" at ").append(System.currentTimeMillis()).toString());
    }

    public void unpark() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        parkingPlaces.put(parkingPlaceTaken);
        logger.info(stringBuilder.append(toString()).append(" just leaved the parking place ").
                    append(parkingPlaceTaken).append(" at ").append(System.currentTimeMillis()).toString());
        parkingPlaceTaken = 0;
    }

    @Override
    public String toString() {
        return "Car № " + id;
    }
}
