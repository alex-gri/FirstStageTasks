package maintask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    static Logger logger = Logger.getLogger(Runner.class.getName());
    static File folder = new File(String.format("%s\\module21\\src\\main\\resources\\Output",
                         System.getProperty("user.dir")));
    static String pathToFile = null;

    static {
        folder.mkdir();
        pathToFile = folder.getPath() + File.separator + "MainTask.txt";
    }

    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            if (!file.exists()) {
                logger.log(Level.SEVERE, "Invalid path");
            } else {
                if (file.isDirectory()) {
                    logger.log(Level.SEVERE, args[0]);
                    writeTreeToFile(args[0]);
                } else {
                    printFileInfo(args[0]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Provide path as an argument!");
        }
    }

    public static void writeTreeToFile(String pathToDir) {
        File dir = new File(pathToDir);
        try {
            File[] dirContent = dir.listFiles();
            for (int i = 0; i < dirContent.length; i++) {
                if (dirContent[i].isFile()) {
                    writeLineToFile(" |  " + dirContent[i].getName());
                } else {
                    writeLineToFile(" |-----" + dirContent[i].getName());
                    writeTreeToFile(dirContent[i].getAbsolutePath());
                }
            }
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Path should include at least one directory, not just local drive!");
        }
    }

    private static void writeLineToFile(String name) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToFile, true)))) {
            writer.printf("%s%n", name);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can not write the line!");
        }
    }

    private static void printFileInfo(String pathToFile) {
        List<String> linesOfFile = new ArrayList<>();
        int numberOfFolders = 0;
        int numberOfFiles = 0;
        int averageFilePerFolder = 0;
        int averageFileNameLength = 0;
        int totalNameLength = 0;
        readFileToList(pathToFile, linesOfFile);
        for (String line : linesOfFile) {
            if (line.contains("-----")) {
                numberOfFolders++;
            } else {
                numberOfFiles++;
                totalNameLength += line.length() - 4;
            }
        }
        averageFileNameLength = getAverage(numberOfFiles, totalNameLength);
        averageFilePerFolder = getAverage(numberOfFolders, numberOfFiles);
        String values = String.format("%nFolders: %d %nFiles: %d %nAverage file per folder: %d " +
                        "%nAverage name length %d", numberOfFolders, numberOfFiles,
                        averageFilePerFolder, averageFileNameLength);
        logger.log(Level.SEVERE, values);
    }

    private static int getAverage(int divisor, int dividend) {
        if (divisor != 0) {
            return dividend / divisor;
        } else {
         return 0;
        }
    }

    private static void readFileToList(String pathToFile, List<String> linesOfFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            bufferedReader.lines().forEach(linesOfFile::add);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
