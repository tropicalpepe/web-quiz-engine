import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        if (size <= 1)
            System.out.println(true);

        int[] elements = new int[size];

        for (int i = 0; i < size; i++) {
            elements[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 1; i < size; i++) {
            boolean nIsBeforeM = elements[i-1] == n && elements[i] == m;
            boolean mIsBeforeN = elements[i-1] == m && elements[i] == n;
            if (nIsBeforeM || mIsBeforeN) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }
}