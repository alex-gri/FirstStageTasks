package fundamentalstasks.optionaltask;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//Задание. Ввести n чисел с консоли.
//        1.     Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
//        2.     Вывести числа в порядке возрастания (убывания) значений их длины.
//        3.     Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.

public class OptionalTask1 {

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        System.out.print("Задайте количество чисел n = ");
        n = input.nextInt();
        Number[] numbers = new Number[n];

        dataInput(n, input, numbers); // ввод чисел

        Arrays.sort(numbers, Comparator.comparingInt(Number -> Number.length));
        System.out.println("Задание 2. Печать введенных чисел в порядке возрастания значений их длины:");
        printArray(numbers); // вывод чисел

        System.out.println("Задание 1. Самое короткое число " + numbers[0].value + " имеет длину " + numbers[0].length);
        System.out.println("Задание 1. Самое длинное число " + numbers[n - 1].value + " имеет длину " + numbers[n - 1].length);

        int start = 0;
        int end = numbers.length - 1;
        quickSort(numbers, start, end); // быстрая сортировка по убыванию
        System.out.println("Печать введенных чисел в порядке убывания значений их длины:");
        printArray(numbers); // вывод чисел

        int averageLength = calcAverage(n, numbers); // Средняя длина по всем числам
        System.out.println("Задание 3. Средняя длина по всем числам = " + averageLength);
        System.out.println("Задание 3. Следующие числа имеют длину меньше средней: ");
        for (int i = 0; i < n; i++) {
            if(numbers[i].length<averageLength)
            {
                System.out.println(numbers[i].value);
            }
        }

        System.out.println("Задание 3. Следующие числа имеют длину больше средней: ");
        for (int i = 0; i < n; i++) {
            if(numbers[i].length>averageLength)
            {
                System.out.println(numbers[i].value);
            }
        }
    }

    private static int calcAverage(int n, Number[] numbers) {
        int averageLength = 0;
        for (int i = 0; i < n; i++) {
            averageLength += numbers[i].length;
        }
        return averageLength /= n;
    }

    private static void dataInput(int n, Scanner input, Number[] numbers) {
        for (int i = 0; i < n; i++) {
            System.out.print("Введите [" + i + "] число: ");
            numbers[i] = new Number(input.nextInt());
        }
    }

    private static void printArray(Number[] numbers) {
        for (Number elem : numbers) {
            System.out.println(elem.value);
        }
    }

    // Метод быстрой сортировки. Все-таки компаратором немного нечестно.
    private static void quickSort(Number[] array, int start, int end) {
        if (array.length == 0)
            return;

        if (start >= end)
            return;

        int index = start + (end - start) / 2;
        Number middle = array[index];

        int i = start, j = end;
        while (i <= j) {
            while (array[i].length > middle.length) {
                i++;
            }

            while (array[j].length < middle.length) {
                j--;
            }

            if (i <= j) {
                Number temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        quickSort(array, start, j);
        quickSort(array, i, end);
    }
}
