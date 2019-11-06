package collectionstasks.maintask;

import collectionstasks.maintask.cars.BusinessCar;
import collectionstasks.maintask.cars.Car;
import collectionstasks.maintask.cars.ComfortCar;
import collectionstasks.maintask.cars.LuxuryCar;
import collectionstasks.maintask.cars.enumfields.FuelType;
import collectionstasks.maintask.cars.enumfields.Manufacturer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

    static Scanner consoleInput = new Scanner(System.in);

    public static void main(String[] args) {

        TaxiPark taxiPark = new TaxiPark(loadCarsFromFile());
        while (true) {
            popMenu();
            switch (consoleInput.nextInt()) {
                case 1: {
                    System.out.println("Стоимость всего автопарка = " + taxiPark.totalParkCost() + " $");
                    break;
                }
                case 2: {
                    taxiPark.sortByFuelConsumption();
                    System.out.println(taxiPark.toString());
                    break;
                }
                case 3: {
                    System.out.print("Найти машины с максимальной скоростью от: ");
                    int startRange = consoleInput.nextInt();
                    System.out.print("До: ");
                    int endRange = consoleInput.nextInt();
                    List<?extends Car> carsInRange = taxiPark.getCarsBySpeedRange(startRange, endRange);
                    for (Car car: carsInRange) {
                        System.out.println(car.toString());
                    }
                    System.out.println();
                    break;
                }
                case 4: {
                    System.out.println(taxiPark.toString());
                    break;
                }
                case 5: {
                    System.exit(0);
                }
                default:
                    System.out.println("Неверный ввод! Попробуйте еще раз.");
            }
        }
    }

    public static void popMenu() {
        System.out.println("Главное меню:");
        System.out.println("1 - Посчитать стоимость всего парка");
        System.out.println("2 - Отсортировать авто по расходу топлива");
        System.out.println("3 - Найти авто с заданными параметрами скорости:");
        System.out.println("4 - Вывести список автомобилей");
        System.out.println("5 - Выход");
        System.out.print("Ваш выбор: ");
    }

    public static List<Car> loadCarsFromFile() {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("src\\main\\resources\\CarsInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.getMessage();
        }
        ArrayList<String> list = new ArrayList<String>();
        while (fileReader.hasNextLine()) {
            list.add(fileReader.nextLine());
        }
        fileReader.close();

        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < list.size(); i++) {
            String[] lineOfCarParameters = list.get(i).trim().split("/");
            if (lineOfCarParameters.length == 14) {
                cars.add(new ComfortCar(lineOfCarParameters[0], Manufacturer.valueOf(lineOfCarParameters[1]),
                        lineOfCarParameters[2], Integer.valueOf(lineOfCarParameters[3]), FuelType.valueOf(lineOfCarParameters[4]),
                        Double.valueOf(lineOfCarParameters[5]), Integer.valueOf(lineOfCarParameters[6]),
                        Integer.valueOf(lineOfCarParameters[7]), Integer.valueOf(lineOfCarParameters[8]),
                        Integer.valueOf(lineOfCarParameters[9]), lineOfCarParameters[10],
                        lineOfCarParameters[11], Boolean.valueOf(lineOfCarParameters[12]),
                        Integer.valueOf(lineOfCarParameters[13])));
            } else if (lineOfCarParameters.length == 18) {
                cars.add(new BusinessCar(lineOfCarParameters[0], Manufacturer.valueOf(lineOfCarParameters[1]),
                        lineOfCarParameters[2], Integer.valueOf(lineOfCarParameters[3]), FuelType.valueOf(lineOfCarParameters[4]),
                        Double.valueOf(lineOfCarParameters[5]), Integer.valueOf(lineOfCarParameters[6]),
                        Integer.valueOf(lineOfCarParameters[7]), Integer.valueOf(lineOfCarParameters[8]),
                        Integer.valueOf(lineOfCarParameters[9]), lineOfCarParameters[10],
                        lineOfCarParameters[11], Boolean.valueOf(lineOfCarParameters[12]),
                        Integer.valueOf(lineOfCarParameters[13]), Integer.valueOf(lineOfCarParameters[14]),
                        Integer.valueOf(lineOfCarParameters[15]), lineOfCarParameters[16],
                        Boolean.valueOf(lineOfCarParameters[17])));
            } else {
                cars.add(new LuxuryCar(lineOfCarParameters[0], Manufacturer.valueOf(lineOfCarParameters[1]),
                        lineOfCarParameters[2], Integer.valueOf(lineOfCarParameters[3]),
                        FuelType.valueOf(lineOfCarParameters[4]),
                        Double.valueOf(lineOfCarParameters[5]), Integer.valueOf(lineOfCarParameters[6]),
                        Integer.valueOf(lineOfCarParameters[7]), Integer.valueOf(lineOfCarParameters[8]),
                        Integer.valueOf(lineOfCarParameters[9]), lineOfCarParameters[10],
                        lineOfCarParameters[11], Boolean.valueOf(lineOfCarParameters[12]),
                        Integer.valueOf(lineOfCarParameters[13]), Integer.valueOf(lineOfCarParameters[14]),
                        Integer.valueOf(lineOfCarParameters[15]), lineOfCarParameters[16],
                        Boolean.valueOf(lineOfCarParameters[17]),
                        lineOfCarParameters[18], Boolean.valueOf(lineOfCarParameters[19]),
                        lineOfCarParameters[20], lineOfCarParameters[21]));
            }
        }
        return cars;
    }
}

