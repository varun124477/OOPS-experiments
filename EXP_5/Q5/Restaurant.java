public class Restaurant {
    public static void main(String[] args) {
        // Array of Chef references holding different subclass objects
        Chef[] chefs = {new ItalianChef(), new ChineseChef(), new MexicanChef()};

        // Loop to invoke makeSpecialDish() on each chef
        for (Chef chef : chefs) {
            chef.makeSpecialDish();  // Calls the overridden method based on the actual object
        }
    }
}
