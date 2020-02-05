package maintask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {

    // log4j logger which configured using log4j2.xml from resources.
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
            if (parkingPlaceTaken != 0) {
                unpark();
            }
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void park() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            // Car waits 2 seconds for available parking place or leaves forever.
            parkingPlaceTaken = parkingPlaces.poll(2, TimeUnit.SECONDS);
            int uniqueSleepTime = random.nextInt(2000);
            Thread.sleep(uniqueSleepTime);
            String logLine = stringBuilder.append(toString()).append(" parked at ").
                           append(parkingPlaceTaken).append(" at ").append(System.currentTimeMillis()).toString();
            logger.info(logLine);
        } catch (NullPointerException e) {
            logger.warn(stringBuilder.append(toString()).append(" is leaving to another place"));
        }
    }

    public void unpark() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        int uniqueSleepTime = random.nextInt(2000);
        Thread.sleep(uniqueSleepTime);
        parkingPlaces.put(parkingPlaceTaken);
        String logLine = stringBuilder.append(toString()).append(" just leaved the parking place ").
                       append(parkingPlaceTaken).append(" at ").append(System.currentTimeMillis()).toString();
        logger.info(logLine);
        parkingPlaceTaken = 0;
    }

    @Override
    public String toString() {
        return "Car № " + id;
    }
}
