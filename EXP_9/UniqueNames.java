import java.util.Arrays;
import java.util.HashSet;

public class UniqueNames {
    public static void main(String[] args) {
        String[] names = {"Aman", "Bhargav", "Sourabh", "Varchasv", "Aman"};
        HashSet<String> uniqueNames = new HashSet<>(Arrays.asList(names));

        String nameToCheck = "Sourabh";
        System.out.println("Is " + nameToCheck + " present? " + uniqueNames.contains(nameToCheck));

        System.out.println("Unique names: " + uniqueNames);
    }
}
