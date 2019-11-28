package optionaltasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstTask {
    static Random random = new Random();
    static Logger logger = Logger.getLogger(FirstTask.class.getName());

    public static void main(String[] args) {
        List<Integer> numbers = getRandomNumbers();
        writeRandomNumbersToFile(numbers);
    }

    private static ArrayList<Integer> getRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        int numbersAmount = random.nextInt(50) + 1;
        for (int i = 0; i < numbersAmount; i++) {
            numbers.add(random.nextInt(100));
        }
        return numbers;
    }

    private static void writeRandomNumbersToFile(Collection numbers) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("OptionalTask1.txt")))) {
            Iterator iterator = numbers.iterator();
            while (iterator.hasNext()) {
                writer.println(iterator.next());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
