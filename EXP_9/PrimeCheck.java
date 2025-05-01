import java.util.ArrayList;

public class PrimeCheck {
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numberList = new ArrayList<>();
        // Autoboxing: adding primitive ints to ArrayList<Integer>
        for (int i = 2; i <= 10; i++) {
            numberList.add(i);
        }

        for (Integer num : numberList) {
            int value = num; // Unboxing
            System.out.println(value + " is " + (isPrime(value) ? "a prime number" : "not a prime number"));
        }
    }
}
