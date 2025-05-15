import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amountOfElements = Integer.parseInt(scanner.nextLine());

        String[] elements = scanner.nextLine().split("\\s+");

        int sum = Arrays
                .stream(elements)
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(sum);

        scanner.close();
    }
}