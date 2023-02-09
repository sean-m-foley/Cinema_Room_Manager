import java.util.Scanner;

public class Main {

    public static long factorial(long n) {
        // write your code here
        long dong = n > 0 ? n : 1;
        // has handle 0 case independently,
        // algo below doesn't work if n = 0;
        if (n > 1) {
            dong *= factorial(n - 1);
        } else {
            return dong;
        }

        return dong;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Integer.parseInt(scanner.nextLine().trim());
        System.out.println(factorial(n));
    }
}