package collectionstasks.maintask.cars.enumfields;

public enum Manufacturer {
    AUDI("Audi"),
    BMW("BMW"),
    MERCEDES("Mercedes-Benz"),
    VW("Volkswagen"),
    VOLVO("Volvo");

    public String manufacturerName;

    Manufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
