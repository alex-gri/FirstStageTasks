package optionaltasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * При разработке для вывода результатов создавать новую директорию и файл средствами класса File.
 * 9. Из текста Java-программы удалить все виды комментариев.
 */

public class ThirdTask {
    static Logger logger = Logger.getLogger(ThirdTask.class.getName());
    static String pathToSource = String.format("%s\\module21\\src\\main\\" +
                                 "java\\optionaltasks\\SecondTask.java", System.getProperty("user.dir"));
    static File folder = new File(String.format("%s\\module21\\src\\main\\resources\\Output",
                         System.getProperty("user.dir")));
    static String pathToNewFile = null;

    static {
        folder.mkdir();
        pathToNewFile = folder.getPath() + File.separator + "OptionalTask3.txt";
    }

    public static void main(String[] args) {
        List<String> sourceLines = readFileToList(pathToSource);
        List<String> sourceLinesWithoutComments = removeCommentLines(sourceLines);
        writeListToFile(sourceLinesWithoutComments);
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

    private static boolean isCommentLine(String line) {
        return line.contains("//") || line.contains("*");
    }

    private static ArrayList<String> removeCommentLines(List<String> sourceLines) {
        ArrayList<String> sourceLinesWithoutComments = new ArrayList<>();
        for (String line: sourceLines) {
            if (isCommentLine(line)) {
                if (line.contains("//")) {
                    sourceLinesWithoutComments.add(line.replace(line.substring(line.indexOf("//")), ""));
                }
                // No else here because we just skip Javadoc comment-lines.
            } else {
                sourceLinesWithoutComments.add(line);
            }
        }
        return sourceLinesWithoutComments;
    }

    private static void writeListToFile(List<String> sourceLinesWithoutComments) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToNewFile)))) {
            Iterator iterator = sourceLinesWithoutComments.iterator();
            while (iterator.hasNext()) {
                writer.println(iterator.next());
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
