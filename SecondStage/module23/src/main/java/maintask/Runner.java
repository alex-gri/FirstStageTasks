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

    public static void main(String[] args) {

        // Parking itself (1 parking place).
        BlockingQueue<Integer> parkingPlaces = initializeParkingPlaces();

        // 15 cars (implements Runnable).
        List<Car> cars = initializeCars(parkingPlaces);

        new Thread(() -> {
            for (int i = 0; i < cars.size(); i++) {
                new Thread(cars.get(i)).start();
            }
        }).start();
    }

    public static ArrayList<Car> initializeCars(BlockingQueue<Integer> parkingPlaces) {
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            cars.add(new Car(i, parkingPlaces));
        }
        return cars;
    }

    public static ArrayBlockingQueue<Integer> initializeParkingPlaces() {
        ArrayBlockingQueue<Integer> parkingPlaces = new ArrayBlockingQueue<>(7);
        for (int i = 1; i < 8; i++) {
            parkingPlaces.add(i);
        }
        return parkingPlaces;
    }
}
