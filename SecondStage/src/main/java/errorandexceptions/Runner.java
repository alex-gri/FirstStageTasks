package errorandexceptions;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

public class Runner {

    static Scanner consoleInput = new Scanner(System.in);
    static Random random = new Random();
    static int exitCounter = 0;
    static University university = new University("Polessky State University");
    static String yourChoice = "Ваш выбор: ";

    public static void main(String[] args) {
        while (true) {
            if (shouldBreak()) break;
            popMenu();
            switch (consoleInput.nextInt()) {
                case 1:
                    printRandomStudentAverageGrade(university);
                    exitCounter++;
                    break;
                case 2: {
                    getAverageGradeOfUniversityWithParameters();
                    exitCounter++;
                }
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
                    System.out.println("Ошибка выбора! Попробуйте снова!");
                    break;
            }
        }
    }

    private static void getAverageGradeOfUniversityWithParameters() {
        System.out.println("Выберите факультет: ");
        university.printFacultiesNames();
        System.out.print(yourChoice);
        Faculty chosenFaculty = university.getFaculties().get(consoleInput.nextInt()-1);

        System.out.print("Выберите группу: ");
        System.out.println(chosenFaculty.printGroups());
        System.out.print(yourChoice);
        Group chosenGroup = chosenFaculty.getGroups().get(consoleInput.nextInt()-1);

        System.out.println("Выберите предмет: ");
        for (int i = 0; i < chosenGroup.getSubjects().size(); i++) {
            System.out.println((i+1) + " " + chosenGroup.getSubjects().get(i).toString());
        }
        System.out.print(yourChoice);
        Subject chosenSubject = chosenGroup.getSubjects().get(consoleInput.nextInt()-1);

        System.out.println("На факультете " + chosenFaculty.getName() + " в группе " + chosenGroup.getId()
                + " по предмету " + chosenSubject.toString() + " средний балл равен = " +
                chosenGroup.getAverageGradeAtSubject(chosenSubject));
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
        System.out.println("Выберите предмет: ");
        for (int i = 0; i < Subject.values().length; i++) {
            System.out.println((i+1) + " - " + Subject.values()[i].toString());
        }
        System.out.print(yourChoice);
        Subject chosenSubject = Subject.values()[consoleInput.nextInt()-1];
        double sumOfUniversityGradesAtSubject = 0;
        double numberOfGroupsThatHasSubject = 0;
        for (Faculty faculty: university.getFaculties()) {
            for (Group group: faculty.getGroups()) {
                if(group.getSubjects().contains(chosenSubject)){
                    for (Student student: group.getStudents()) {
                        for (Map.Entry<Subject, List<Integer>> pair : student.getGrades().entrySet()) {
                            if(pair.getKey().equals(chosenSubject)){
                                Iterator<Integer> iterator = pair.getValue().iterator();
                                while(iterator.hasNext()){
                                    sumOfUniversityGradesAtSubject += iterator.next();
                                    numberOfGroupsThatHasSubject++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(numberOfGroupsThatHasSubject != 0)
        {
            return sumOfUniversityGradesAtSubject / numberOfGroupsThatHasSubject;
        }else{
            return 0;
        }
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
        System.out.print(yourChoice);
    }
}
