package optionaltasks;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание. Выполнить указанные действия по чтению информации из файла, ее обработке и записи в файл.
 * При разработке для вывода результатов создавать новую директорию и файл средствами класса File.
 * 1. Создать и заполнить файл случайными целыми числами. Отсортировать содержимое файла по возрастанию.
 */

public class FirstTask {
    static Random random = new Random();
    static Logger logger = Logger.getLogger(FirstTask.class.getName());
    static File folder = new File("Output");
    static String pathToFile = null;

    static {
        folder.mkdir();
        pathToFile = folder.getPath() + File.separator + "OptionalTask1.txt";
    }

    public static void main(String[] args) {
        List<Integer> randomNumbers = getRandomNumbers();
        writeNumbersToFile(randomNumbers);
        TreeSet<Integer> sortedNumbers = getSortedNumbersFromFile();
        writeNumbersToFile(sortedNumbers);
    }

    private static ArrayList<Integer> getRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        int numbersAmount = random.nextInt(50) + 1;
        for (int i = 0; i < numbersAmount; i++) {
            numbers.add(random.nextInt(100));
        }
        return numbers;
    }

    private static void writeNumbersToFile(Collection numbers) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToFile)))) {
            Iterator iterator = numbers.iterator();
            while (iterator.hasNext()) {
                writer.println(iterator.next());
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    private static TreeSet<Integer> getSortedNumbersFromFile() {
        TreeSet<Integer> numbers = new TreeSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return numbers;
    }
}
