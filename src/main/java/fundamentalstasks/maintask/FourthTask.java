package fundamentalstasks.maintask;

public class FourthTask {
    public static void main(String[] args) {
        int size = args.length;
        int sum = 0;
        int multi = 1;

        // Чтобы не парсить значение аргумента дважды за итерацию.
        int temp;
        for (int i = 0; i < size; i++) {
            temp = Integer.parseInt(args[i]);
            sum += temp;
            multi *= temp;
        }
        if(args.length!=0) {
            System.out.println("Сумма аргументов = " + sum);
            System.out.println("Произведение аргументов = " + multi);
        }
        else {
            System.out.println("Аргументы отсутствуют");
        }
    }
}
