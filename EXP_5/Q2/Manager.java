public class Manager extends Employee {
    private String department;

    public Manager(String name, int empId, double salary, String department) {
        super(name, empId, salary);
        this.department = department;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Department: " + department);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", 101, 50000);
        Manager m1 = new Manager("Bob", 102, 70000, "IT");

        e1.increaseSalary(10);
        m1.increaseSalary(15);

        e1.display();
        m1.display();
    }
}