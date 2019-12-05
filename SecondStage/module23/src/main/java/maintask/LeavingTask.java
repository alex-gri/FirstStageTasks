package maintask;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class LeavingTask implements Runnable {
    static Logger logger = Logger.getLogger(LeavingTask.class.getName());
    private final BlockingQueue<Car> parkedCars;

    public LeavingTask(BlockingQueue<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    @Override
    public void run() {
        leaveParkingPlace();
    }

    public void leaveParkingPlace() {
        try {
            parkedCars.take().leaveParkingPlace();
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
