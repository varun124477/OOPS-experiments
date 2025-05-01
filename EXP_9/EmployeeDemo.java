import java.util.ArrayList;

class Employee {
    String name;
    int id;
    double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Alice", 101, 50000));
        employees.add(new Employee("Bob", 102, 60000));
        employees.add(new Employee("Charlie", 103, 55000));

        // Update salary of employee with id 102
        for (Employee emp : employees) {
            if (emp.getId() == 102) {
                emp.setSalary(65000);
            }
        }

        // Remove employee with id 101
        employees.removeIf(emp -> emp.getId() == 101);

        // Print remaining employees
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
