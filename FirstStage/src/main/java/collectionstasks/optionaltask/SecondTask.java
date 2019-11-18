package collectionstasks.optionaltask;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//   2.   Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.

public class SecondTask {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Scanner consoleInput = new Scanner(System.in);
        System.out.print("Введите число: ");
        String inputNumber = consoleInput.next();
        for (int i = 0; i < inputNumber.length(); i++) {
            stack.push(Character.digit(inputNumber.charAt(i), 10));
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}

