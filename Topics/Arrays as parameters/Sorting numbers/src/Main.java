import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void sort(int[] numbers) {
        // This uses a revised bubble sort to skip pre-sorted
        // arrays. It's O(n²) for unsorted, O(n) for sorted
        // a normal bubble sort will run O(n²) even on a pre-sorted
        // array

        for (int i = 0; i < numbers.length; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    isSwapped = true;
                }
            }

            if (!isSwapped) {
                // array is already sorted, so get out
                break;
            }
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split("\\s+");
        int[] numbers = Arrays.stream(values)
                .mapToInt(Integer::parseInt)
                .toArray();
        sort(numbers);
        Arrays.stream(numbers).forEach(e -> System.out.print(e + " "));
    }
}
