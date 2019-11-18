package fundamentalstasks.maintask;

import java.util.Random;
import java.util.Scanner;

public class ThirdTask {
    public static void main(String[] args) {
        Random generator = new Random();
        Scanner input = new Scanner(System.in);

        System.out.print("Количество чисел в генерации: ");
        int size = input.nextInt();

        for (int i = 0; i < size; i++) {
            System.out.println(generator.nextInt());
        }

        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(generator.nextInt());
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
    }
}
