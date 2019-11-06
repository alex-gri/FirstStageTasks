package fundamentalstasks.maintask;

import java.util.Scanner;

public class FifthTask {
    public static void main(String[] args) {
        int index;
        String month = "";
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Введите число от 1 до 12: ");
            index = input.nextInt();

            if(index > 0 && index < 13)
            {
                break;
            }
            System.out.println("Ошибка! Число не принадлежит указанному диапазону!");
        } while(true);

        switch(index)
        {
            case 1:month="Январь";break;
            case 2:month="Февраль";break;
            case 3:month="Март";break;
            case 4:month="Апрель";break;
            case 5:month="Май";break;
            case 6:month="Июнь";break;
            case 7:month="Июль";break;
            case 8:month="Август";break;
            case 9:month="Сентябрь";break;
            case 10:month="Октябрь";break;
            case 11:month="Ноябрь";break;
            case 12:month="Декабрь";break;
            default:
                System.out.println("Перезапустите программу и попробуйте снова.");break;
        }
        System.out.println(month);
    }
}
