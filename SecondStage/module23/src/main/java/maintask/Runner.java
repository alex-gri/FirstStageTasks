package maintask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Разработать консольное многопоточное приложение.
 * Использовать возможности, предоставляемые пакетом java.util.concurrent.
 * Все сущности, желающие получить доступ к ресурсу, должны быть потоками.
 * № 2. Автостоянка. Доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет на другую стоянку.
 */

public class Runner {

    // 3 parkings always created with 3 parking places at each.
    static List<Parking> parkings = new ArrayList<>();

    public static void main(String[] args) {
        initializeParkings();

        // Blocking parked car in queue so each car can't park twice.
        BlockingQueue<Car> parkedCars = new LinkedBlockingQueue<>(10);

        // Thread that creates car and parks it.
        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                new Thread(new Car(i + 1, parkedCars)).start();
            }
        }).start();

        // Thread that make car leave a parking.
        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                new Thread(new LeavingTask(parkedCars)).start();
            }
        }).start();
    }

    public static void initializeParkings() {
        for (int i = 0; i < 3; i++) {
            parkings.add(new Parking(i + 1));
        }
    }
}
