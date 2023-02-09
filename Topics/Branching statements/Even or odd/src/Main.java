import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int number;
        String answer;

        while (true) {
            number = scanner.nextInt();
            if (number == 0) {
                break;
            } else {
                answer = number % 2 == 1 ? "odd" : "even";
            }
            System.out.println(answer);
        }
    }
}