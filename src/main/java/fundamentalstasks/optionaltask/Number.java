package fundamentalstasks.optionaltask;

public class Number {
    int value;
    int length;

    public Number(int value) {
        this.value = value;
        // Подсчет количества разрядов числа через длину строки
        length = String.valueOf(value).length();
    }
}
