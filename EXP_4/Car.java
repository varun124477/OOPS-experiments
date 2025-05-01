public class Car {
    String make;
    String model;
    int year;

    // Constructor using this keyword
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayCarDetails() {
        System.out.println("Car Make: " + make + ", Model: " + model + ", Year: " + year);
    }

    public static void main(String[] args) {
        Car myCar = new Car("Tesla", "Model 3", 2025);
        myCar.displayCarDetails();
    }
}
