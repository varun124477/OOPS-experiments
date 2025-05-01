public class Employee {
    protected String name;
    protected int empId;
    protected double salary;

    // Default Constructor
    public Employee() {
        this.name = "Unknown";
        this.empId = 0;
        this.salary = 0.0;
    }

    // Parameterized Constructor
    public Employee(String name, int empId, double salary) {
        this.name = name;
        this.empId = empId;
        this.salary = salary;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    // Method to increase salary
    public void increaseSalary(double percentage) {
        salary += salary * (percentage / 100);
    }

    public void display() {
        System.out.println("Employee ID: " + empId + ", Name: " + name + ", Salary: $" + salary);
    }
}


