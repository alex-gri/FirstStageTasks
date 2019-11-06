package fundamentalstasks.maintask;

import java.util.Scanner;

public class FirstTask {
    public static void main(String[] args)  {
        System.out.println("Представьтесь, пожалуйста: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println("Доброго времени суток, " + name + "!");
    }
}
