import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SumUniqueValues {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 5, 3, 8, 2, 5));
        HashSet<Integer> uniqueSet = new HashSet<>(numbers);

        int sum = 0;
        for (int num : uniqueSet) {
            sum += num;
        }

        System.out.println("Input: " + numbers);
        System.out.println("Unique values: " + uniqueSet);
        System.out.println("Sum: " + sum);
    }
}
