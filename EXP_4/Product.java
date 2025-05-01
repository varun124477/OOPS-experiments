public class Product {
    static int totalProducts = 0;

    int productId;
    String productName;
    String category;
    private double price;

    // Default Constructor
    public Product() {
        this.productId = 0;
        this.productName = "Notes";
        this.category = "General";
        this.price = 0.0;
        totalProducts++;
    }

    // Parameterized Constructor
    public Product(int productId, String productName, String category, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        totalProducts++;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Display product details
    public void displayProductInfo() {
        System.out.println("Product ID: " + productId + ", Name: " + productName + ", Category: " + category + ", Price: $" + price);
    }

    // Static method to display total product count
    public static void displayTotalProducts() {
        System.out.println("Total Products: " + totalProducts);
    }

    // Method to calculate stock value (Overloaded)
    public double calculateStockValue(int quantity) {
        return price * quantity;
    }

    public double calculateStockValue(int quantity, double discountRate) {
        double discountedPrice = price * (1 - discountRate / 100);
        return discountedPrice * quantity;
    }

    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop", "Electronics", 1200.00);
        Product p2 = new Product(102, "Smartphone", "Electronics", 800.00);
        Product p3 = new Product(); // Uses default values

        p1.displayProductInfo();
        System.out.println("Stock Value (10 units): $" + p1.calculateStockValue(10));
        System.out.println("Stock Value (10 units, 10% discount): $" + p1.calculateStockValue(10, 10));

        p2.displayProductInfo();
        p3.displayProductInfo();

        Product.displayTotalProducts();
    }
}
