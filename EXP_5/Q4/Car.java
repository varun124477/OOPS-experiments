public class Car extends Vehicle {
    public Car(String make, String model) {
        super(make, model);
    }

    @Override
    public void startEngine() {
        System.out.println(make + " " + model + "'s engine started.");
        }

        @Override
        public void stopEngine() {
        System.out.println(make + " " + model + "'s engine stopped.");
    }

    public static void main(String[] args) {
        Car myCar = new Car("Kawasaki", "Ninja");
        myCar.startEngine();
        myCar.serviceInfo();
        myCar.stopEngine();
    }
}