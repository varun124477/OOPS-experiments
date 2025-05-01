public class Customer {
    private double balance = 0.0;

    // Public method to add balance (double type)
    public void addBalance(double amount) {
        balance += amount;
        System.out.println("Added: $" + amount + " New Balance: $" + balance);
    }

    // Overloaded method for int amount
    public void addBalance(int amount) {
        balance += amount;
        System.out.println("Added: $" + amount + " New Balance: $" + balance);
    }

    // Protected method to deduct balance
    protected void deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Deducted: $" + amount + " New Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Default-access method to show balance
    void showBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.addBalance(100);
        customer.addBalance(50.75);
        customer.showBalance();
        customer.deductBalance(30);
        customer.showBalance();
    }
}
