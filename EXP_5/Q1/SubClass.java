public class SubClass extends Superclass {
    public void display() {
        System.out.println("Accessing private member via getter: " + getPrivateValue());
    }

    public static void main(String[] args) {
        SubClass obj = new SubClass();
        obj.display();
    }
}
