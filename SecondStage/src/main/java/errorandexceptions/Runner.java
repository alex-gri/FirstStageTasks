package errorandexceptions;

import java.sql.SQLOutput;
import java.util.*;

public class Runner {

    static Scanner consoleInput = new Scanner(System.in);
    static Random random = new Random();
    static int exitCounter = 0;
    static University university = new University("Polessky State University");
    static String yourChoiceMessage = "Ваш выбор: ";
    static String inputErrorMessage = "Ошибка выбора! Попробуйте снова!";

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
                        getAverageGradeOfUniversityWithParameters();
                        exitCounter++;
                        break;
                    case 3:
                        System.out.println("Средняя оценка по всему университету = " +
                                getAverageGradeOfUniversityAtSubject());
                        exitCounter++;
                        break;
                    case 4:
                        System.out.println(university.toString());
                        exitCounter++;
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(inputErrorMessage);
                        break;
                }
            } else {
                System.out.println(inputErrorMessage);
                consoleInput = new Scanner(System.in);
            }
        }
    }

    private static void getAverageGradeOfUniversityWithParameters() {
        Faculty chosenFaculty = chooseFaculty();
        Group chosenGroup = chooseGroup(chosenFaculty);
        Subject chosenSubject = chooseSubject(chosenGroup);
        System.out.println("На факультете " + chosenFaculty.getName() + " в группе " + chosenGroup.getId()
                + " по предмету " + chosenSubject.toString() + " средний балл равен = " +
                chosenGroup.getAverageGradeAtSubject(chosenSubject));
    }

    private static Subject chooseSubject(Group chosenGroup) {
        consoleInput = new Scanner(System.in);
        System.out.println("Выберите предмет: ");
        for (int i = 0; i < chosenGroup.getSubjects().size(); i++) {
            System.out.println((i + 1) + " " + chosenGroup.getSubjects().get(i).toString());
        }
        int indexOfSubject = 0;

        // Input value validation.
        while(true){
            try{
                System.out.print(yourChoiceMessage);
                indexOfSubject = consoleInput.nextInt();
                if(indexOfSubject > 0 && indexOfSubject <= chosenGroup.getSubjects().size())
                    break;
                else{
                    throw new IndexOutOfBoundsException("Ошибка! Выберите один из предложенных вариантов!");
                }
            }catch(InputMismatchException | IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println(inputErrorMessage);
                consoleInput = new Scanner(System.in);
            }
        }
        return chosenGroup.getSubjects().get(indexOfSubject - 1);
    }

    private static Group chooseGroup(Faculty chosenFaculty) {
        System.out.print("Выберите группу: ");
        System.out.println(chosenFaculty.printGroups());
        int indexOfGroup = 0;

        // Input value validation.
        while(true){
            try{
                System.out.print(yourChoiceMessage);
                indexOfGroup = consoleInput.nextInt();
                if(indexOfGroup > 0 && indexOfGroup <= chosenFaculty.getGroups().size())
                    break;
                else{
                    throw new IndexOutOfBoundsException("Ошибка! Выберите один из предложенных вариантов!");
                }
            }catch(InputMismatchException | IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println(inputErrorMessage);
                consoleInput = new Scanner(System.in);
            }
        }
        return chosenFaculty.getGroups().get(indexOfGroup - 1);
    }

    private static Faculty chooseFaculty() {
        System.out.println("Выберите факультет: ");
        university.printFacultiesNames();
        int indexOfFaculty = 0;

        // Валидация вводимого значения.
        while(true){
            try{
                System.out.print(yourChoiceMessage);
                indexOfFaculty = consoleInput.nextInt();
                if(indexOfFaculty > 0 && indexOfFaculty <= university.getFaculties().size())
                    break;
                else{
                    throw new IndexOutOfBoundsException("Ошибка! Выберите один из предложенных вариантов!");
                }
            }catch(InputMismatchException | IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println(inputErrorMessage);
                consoleInput = new Scanner(System.in);
            }
        }
        return university.getFaculties().get(indexOfFaculty - 1);
    }

    private static boolean shouldBreak() {
        if (exitCounter == 4) {
            System.out.println("Программа закрыта автоматически (4 итерации).");
            return true;
        } else {
            System.out.println("Программа будет автоматически закрыта через " + (4 - exitCounter) + " итерации");
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
                    for (Student student : group.getStudents()) {
                        for (Map.Entry<Subject, List<Integer>> pair : student.getGrades().entrySet()) {
                            if (pair.getKey().equals(chosenSubject)) {
                                Iterator<Integer> iterator = pair.getValue().iterator();
                                while (iterator.hasNext()) {
                                    sumOfUniversityGradesAtSubject += iterator.next();
                                    numberOfGroupsThatHasSubject++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return (numberOfGroupsThatHasSubject != 0) ? sumOfUniversityGradesAtSubject / numberOfGroupsThatHasSubject : 0;
    }

    private static Subject chooseSubject() {
        System.out.println("Выберите предмет: ");
        for (int i = 0; i < Subject.values().length; i++) {
            System.out.println((i + 1) + " - " + Subject.values()[i].toString());
        }
        int indexOfSubject = 0;

        // Input value validation.
        while (true) {
            try {
                System.out.print(yourChoiceMessage);
                indexOfSubject = consoleInput.nextInt();
                if(indexOfSubject > 0 && indexOfSubject <= Subject.values().length)
                    break;
                else{
                    throw new IndexOutOfBoundsException("Ошибка! Выберите один из предложенных вариантов!");
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println(inputErrorMessage);
                consoleInput = new Scanner(System.in);
            }
        }
        return Subject.values()[indexOfSubject - 1];
    }

    private static void printRandomStudentAverageGrade(University university) {
        Student randomStudent = getRandomStudent(university);
        System.out.print("Студент " + randomStudent.getInitial() + " имеет средний балл = ");
        System.out.println(randomStudent.averageGradeOfAllSubjects());
    }

    private static Student getRandomStudent(University university) {
        Faculty randomFaculty = university.getFaculties().get(random.nextInt(university.getFaculties().size()));
        Group randomGroup = randomFaculty.getGroups().get(random.nextInt(randomFaculty.getGroups().size()));
        return randomGroup.getStudents().get(random.nextInt(randomGroup.getStudents().size()));
    }

    public static void popMenu() {
        System.out.println("Выберите:");
        System.out.println("1 - Посчитать средний балл по всем предметам случайного студента");
        System.out.println("2 - Посчитать средний балл по конкретному предмету в конкретной группе " +
                "и на конкретном факультете");
        System.out.println("3 - Посчитать средний балл по предмету для всего университета");
        System.out.println("4 - Вывести всю информацию об университете и студентах");
        System.out.println("5 - Выход");
        System.out.print(yourChoiceMessage);
    }
}
