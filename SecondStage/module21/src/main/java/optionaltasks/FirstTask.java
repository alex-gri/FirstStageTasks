package optionaltasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstTask {
    static Random random = new Random();
    static Logger logger = Logger.getLogger(FirstTask.class.getName());

    public static void main(String[] args) {
        writeRandomNumbersToFile();
    }

    private static void writeRandomNumbersToFile() {
        int numbersAmount = random.nextInt(50) + 1;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("OptionalTask1.txt")))) {
            for (int i = 0; i < numbersAmount; i++) {
                writer.println(random.nextInt(100));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
