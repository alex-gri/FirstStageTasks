package errorandexceptions;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    public static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE = "Ошибка! Выберите один из предложенных вариантов!";
    public static final String YOUR_CHOICE_MESSAGE = "Ваш выбор: ";
    public static final String INPUT_ERROR_MESSAGE = "Ошибка выбора! Попробуйте снова!";
    static Logger logger = Logger.getLogger(Runner.class.getName());
    static Scanner consoleInput = new Scanner(System.in);
    static Random random = new Random();
    static int exitCounter = 0;
    static University university = new University("Polessky State University");

    public static void main(String[] args) {
        while (true) {
            if (shouldBreak()) break;
            popMenu();
            if (consoleInput.hasNextInt()) {
                switch (consoleInput.nextInt()) {
                    case 1:
                        printRandomStudentAverageGrade(university);
                        exitCounter++;
                        break;
                    case 2:
                        printAverageGradeOfUniversityWithParameters();
                        exitCounter++;
                        break;
                    case 3:
                        logger.log(Level.SEVERE, "Средняя оценка по всему университету = {0}",
                                getAverageGradeOfUniversityAtSubject());
                        exitCounter++;
                        break;
                    case 4:
                        String infoOfUniversity = university.toString();
                        logger.log(Level.SEVERE, infoOfUniversity);
                        exitCounter++;
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                        break;
                }
            } else {
                logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                consoleInput = new Scanner(System.in);
            }
        }
    }

    private static void printAverageGradeOfUniversityWithParameters() {
        Faculty chosenFaculty = chooseFaculty();
        Group chosenGroup = chooseGroup(chosenFaculty);
        Subject chosenSubject = chooseSubject(chosenGroup);
        String averageGradeOfUniversityWithParametersFormatted = ("На факультете " +
                chosenFaculty.getName() + " в группе " + chosenGroup.getId() +
                " по предмету " + chosenSubject.toString() + " средний балл равен = " +
                chosenGroup.getAverageGradeAtSubject(chosenSubject));
        logger.log(Level.SEVERE, averageGradeOfUniversityWithParametersFormatted);
    }

    private static Subject chooseSubject(Group chosenGroup) {
        consoleInput = new Scanner(System.in);
        logger.log(Level.SEVERE, "Выберите предмет: ");
        for (int i = 0; i < chosenGroup.getSubjects().size(); i++) {
            String chooseLine = (i + 1) + " " + chosenGroup.getSubjects().get(i).toString();
            logger.log(Level.SEVERE, chooseLine);
        }
        int indexOfSubject = 0;

        // Input value validation.
        while (true) {
            try {
                logger.log(Level.SEVERE, YOUR_CHOICE_MESSAGE);
                indexOfSubject = consoleInput.nextInt();
                if (indexOfSubject > 0 && indexOfSubject <= chosenGroup.getSubjects().size())
                    break;
                else {
                    throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, e.getMessage());
                logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                consoleInput = new Scanner(System.in);
            }
        }
        return chosenGroup.getSubjects().get(indexOfSubject - 1);
    }

    private static Group chooseGroup(Faculty chosenFaculty) {
        logger.log(Level.SEVERE, "Выберите группу: ");
        String groupsLines = chosenFaculty.printGroups();
        logger.log(Level.SEVERE, groupsLines);
        int indexOfGroup = 0;

        // Input value validation.
        while (true) {
            try {
                logger.log(Level.SEVERE, YOUR_CHOICE_MESSAGE);
                indexOfGroup = consoleInput.nextInt();
                if (indexOfGroup > 0 && indexOfGroup <= chosenFaculty.getGroups().size())
                    break;
                else {
                    throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, e.getMessage());
                logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                consoleInput = new Scanner(System.in);
            }
        }
        return chosenFaculty.getGroups().get(indexOfGroup - 1);
    }

    private static Faculty chooseFaculty() {
        logger.log(Level.SEVERE, "Выберите факультет: ");
        university.printFacultiesNames();
        int indexOfFaculty = 0;

        // Валидация вводимого значения.
        while (true) {
            try {
                logger.log(Level.SEVERE, YOUR_CHOICE_MESSAGE);
                indexOfFaculty = consoleInput.nextInt();
                if (indexOfFaculty > 0 && indexOfFaculty <= university.getFaculties().size())
                    break;
                else {
                    throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, e.getMessage());
                logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                consoleInput = new Scanner(System.in);
            }
        }
        return university.getFaculties().get(indexOfFaculty - 1);
    }

    private static boolean shouldBreak() {
        if (exitCounter == 4) {
            logger.log(Level.SEVERE, "Программа закрыта автоматически (4 итерации).");
            return true;
        } else {
            logger.log(Level.SEVERE, "Программа будет автоматически закрыта через {0} итерации", (4 - exitCounter));
            return false;
        }
    }

    public static double getAverageGradeOfUniversityAtSubject() {
        Subject chosenSubject = chooseSubject();
        double sumOfUniversityGradesAtSubject = 0;
        double numberOfGroupsThatHasSubject = 0;
        for (Faculty faculty : university.getFaculties()) {
            for (Group group : faculty.getGroups()) {
                if (group.getSubjects().contains(chosenSubject)) {
                    sumOfUniversityGradesAtSubject += group.getAverageGradeAtSubject(chosenSubject);
                    numberOfGroupsThatHasSubject++;
                }
            }
        }
        return (numberOfGroupsThatHasSubject != 0) ? sumOfUniversityGradesAtSubject / numberOfGroupsThatHasSubject : 0;
    }

    private static Subject chooseSubject() {
        logger.log(Level.SEVERE, "Выберите предмет: ");
        for (int i = 0; i < Subject.values().length; i++) {
            String subjectLine = (i + 1) + " - " + Subject.values()[i].toString();
            logger.log(Level.SEVERE, subjectLine);
        }
        int indexOfSubject = 0;

        // Input value validation.
        while (true) {
            try {
                logger.log(Level.SEVERE, YOUR_CHOICE_MESSAGE);
                indexOfSubject = consoleInput.nextInt();
                if (indexOfSubject > 0 && indexOfSubject <= Subject.values().length)
                    break;
                else {
                    throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, e.getMessage());
                logger.log(Level.INFO, INPUT_ERROR_MESSAGE);
                consoleInput = new Scanner(System.in);
            }
        }
        return Subject.values()[indexOfSubject - 1];
    }

    private static void printRandomStudentAverageGrade(University university) {
        Student randomStudent = getRandomStudent(university);
        String randomStudentAverageGradeLine = "Студент " + randomStudent.getInitial() + " имеет средний балл = " +
                randomStudent.getAverageGradeOfAllSubjects();
        logger.log(Level.SEVERE, randomStudentAverageGradeLine);
    }

    private static Student getRandomStudent(University university) {
        Faculty randomFaculty = university.getFaculties().get(random.nextInt(university.getFaculties().size()));
        Group randomGroup = randomFaculty.getGroups().get(random.nextInt(randomFaculty.getGroups().size()));
        return randomGroup.getStudents().get(random.nextInt(randomGroup.getStudents().size()));
    }

    public static void popMenu() {
        String menuLine = "Выберите: \n" +
                "1 - Посчитать средний балл по всем предметам случайного студента\n" +
                "2 - Посчитать средний балл по конкретному предмету в конкретной группе " +
                "и на конкретном факультете\n" +
                "3 - Посчитать средний балл по предмету для всего университета\n" +
                "4 - Вывести всю информацию об университете и студентах\n" +
                "5 - Выход";
        logger.log(Level.SEVERE, menuLine);
        logger.log(Level.INFO, YOUR_CHOICE_MESSAGE);
    }
}
