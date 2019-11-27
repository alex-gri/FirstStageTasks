package maintask;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    static Logger logger = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            logger.log(Level.SEVERE, "Invalid path");
        } else {
            if (file.isDirectory()) {
                logger.log(Level.SEVERE, args[0]);
                writeTree(args[0]);
            }
        }
    }

    public static void writeTree(String pathToDir) {
        File dir = new File(pathToDir);
        File[] dirContent = dir.listFiles();
        for (int i = 0; i < dirContent.length; i++) {
            if (dirContent[i].isFile()) {
                writeLineToFile(" |  " + dirContent[i].getName());
            } else {
                writeLineToFile(" |-----" + dirContent[i].getName());
                writeTree(dirContent[i].getAbsolutePath());
            }
        }
    }

    private static void writeLineToFile(String name) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Tree.txt", true)))) {
            writer.printf("%s%n", name);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can not write the line!");
        }
    }
}
