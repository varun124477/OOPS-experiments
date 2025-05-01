public abstract class Vehicle {
    protected String make, model;

    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public abstract void startEngine();
    public abstract void stopEngine();

    public void serviceInfo() {
        System.out.println("Service your vehicle regularly to maintain performance.");
    }
}