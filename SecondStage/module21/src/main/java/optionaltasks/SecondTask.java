package optionaltasks;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * При разработке для вывода результатов создавать новую директорию и файл средствами класса File.
 * 3. Прочитать текст Java-программы и записать в другой файл в обратном порядке символы каждой строки.
 */

public class SecondTask {
    static Logger logger = Logger.getLogger(SecondTask.class.getName());
    static String pathToSource = System.getProperty("user.dir") + "\\module21\\src\\main\\" +
                                 "java\\optionaltasks\\SecondTask.java";
    static File folder = new File("Output");
    static String pathToNewFile = null;

    static {
        folder.mkdir();
        pathToNewFile = folder.getPath() + File.separator + "OptionalTask2.txt";
    }

    public static void main(String[] args) {
        List<String> sourceLines = readFileToList(pathToSource);
        for (String line: sourceLines) {
            System.out.println(line);
        }
        sourceLines = reflectSourceLines(sourceLines);
        writeListToFile(sourceLines);
    }

    private static ArrayList<String> readFileToList(String pathToFile) {
        ArrayList<String> linesOfFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            bufferedReader.lines().forEach(linesOfFile::add);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return linesOfFile;
    }

    private static ArrayList<String> reflectSourceLines(List<String> sourceLines) {
        ArrayList<String> reflectedSourceLines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: sourceLines) {
            stringBuilder.setLength(0);
            reflectedSourceLines.add(stringBuilder.append(line).reverse().toString());
        }
        return reflectedSourceLines;
    }

    private static void writeListToFile(List<String> reversedSourceLines) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToNewFile)))) {
            Iterator iterator = reversedSourceLines.iterator();
            while (iterator.hasNext()) {
                writer.println(iterator.next());
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
