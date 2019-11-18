package collectionstasks.maintask;

import collectionstasks.maintask.cars.Car;

import java.util.*;

public class TaxiPark {
    private List<? extends Car> cars;

    public TaxiPark(List<? extends Car> cars) {
        this.cars = cars;
    }

    public List<? extends Car> getCars() {
        return cars;
    }

    public int totalParkCost() {
        int totalCost = 0;
        for (Car car : this.cars) { totalCost += car.getCost(); }
        return totalCost;
    }

    public TaxiPark sortByFuelConsumption(){
        Collections.sort(this.cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (o1.getFuelConsumption() < o2.getFuelConsumption()) { return -1; }
                if (o1.getFuelConsumption() > o2.getFuelConsumption()) { return 1; }
                return 0;
            }
        });
        return this;
    }

    public List<? extends Car> getCarsBySpeedRange(int rangeStart, int rangeEnd){
        List<? extends Car> carsInRange = this.getCars();
        ListIterator<? extends Car> listIterator = carsInRange.listIterator();
        while (listIterator.hasNext())
        {
            Car temp = listIterator.next();
            if(!(temp.getMaxSpeed() >= rangeStart && temp.getMaxSpeed() <= rangeEnd))
            {
                listIterator.remove();
            }
        }
        return carsInRange;
    }

    @Override
    public String toString() {
        String taxiParkCarsOutput = "";
        for (Car car: this.cars) {
            taxiParkCarsOutput += car.toString() + "\n";
        }
        return taxiParkCarsOutput;
    }
}
