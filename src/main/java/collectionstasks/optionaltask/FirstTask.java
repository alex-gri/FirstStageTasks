package collectionstasks.optionaltask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//   1.   Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.

public class FirstTask {
    public static void main(String[] args) {
        printLinesFromFile("CarsInfo.txt");
        ArrayList<String> fileContent = readLinesFromFile();
        System.out.println();
        writeLinesToFileInDescOrder(fileContent);
        printLinesFromFile("CarsInfoDESC.txt");
    }

    private static void printLinesFromFile(String filename) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("src\\main\\resources\\" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.getMessage();
        }
        while (fileReader.hasNextLine()) {
            System.out.println(fileReader.nextLine());
        }
        fileReader.close();
    }

    private static void writeLinesToFileInDescOrder(ArrayList<String> fileContent) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src\\main\\resources\\CarsInfoDESC.txt");
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        for (int i = fileContent.size() - 1; i >= 0; i--) {
            try {
                fileWriter.write(fileContent.get(i).toString() + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    private static ArrayList<String> readLinesFromFile() {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("src\\main\\resources\\CarsInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.getMessage();
        }
        ArrayList<String> fileContent = new ArrayList<String>();
        while (fileReader.hasNextLine()) {
            fileContent.add(fileReader.nextLine());
        }
        fileReader.close();
        return fileContent;
    }

}
