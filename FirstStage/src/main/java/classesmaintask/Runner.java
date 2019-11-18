package classesmaintask;

import java.text.SimpleDateFormat;
import java.util.*;

public class Runner {

    static Scanner consoleInputChoice = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Group> groups = new ArrayList<Group>();
        ArrayList<Student.Address> addresses = new ArrayList<Student.Address>();
        ArrayList<Student> students = new ArrayList<Student>();
        initializeGroupsArray(groups);
        initializeAddressesArray(addresses);
        initializeStudentsArray(groups, students, addresses);

        while (true) {
            popMenu();
            switch (consoleInputChoice.nextInt()) {
                case 1:
                    filterByFaculty(students);
                    break;
                case 2:
                    mixedFilter(students);
                    break;
                case 3:
                    filterByBirthday(students);
                    break;
                case 4:
                    filterByGroup(students, groups);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Ошибка выбора! Попробуйте снова!");
            }
        }
    }

    private static void popMenu() {
        System.out.println("Массив студентов жестко задан в коде приложения.");
        System.out.println("Пожалуйста, выберите один из предложенных вариантов:");
        System.out.println("1 - Вывести список студентов N-ого факультета");
        System.out.println("2 - Вывести списки студентов для каждого факультета и курса");
        System.out.println("3 - Вывести список студентов, родившихся после N года");
        System.out.println("4 - Вывести список студентов N-й учебной группы");
        System.out.println("5 - Выход");
        System.out.print("Ваш выбор: ");
    }

    // Вывод списка студентов заданного факультета.
    private static void filterByFaculty(ArrayList<Student> students) {
        System.out.println("Выберите факультет: ");
        System.out.println("1 - " + Faculties.BANKING.facultyName);
        System.out.println("2 - " + Faculties.ECONOMICAL.facultyName);
        System.out.println("3 - " + Faculties.BIOTECHNOLOGICAL.facultyName);
        System.out.print("Ваш выбор: ");
        int indexOfChoice = consoleInputChoice.nextInt() - 1;
        printHeader();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).faculty == Faculties.values()[indexOfChoice]) {
                System.out.println(students.get(i).toString());
            }
        }
        System.out.println();
    }

    // Вывод списка студентов каждого курса в каждом факультете.
    private static void mixedFilter(ArrayList<Student> students) {
        printHeader();
        for (int i = 0; i < Faculties.values().length; i++) {
            System.out.println(Faculties.values()[i].facultyName);
            for (int j = 1; j <= 4; j++) {
                System.out.println("\t" + (j) + " курс:");
                for (int k = 0; k < students.size(); k++) {
                    if (students.get(k).faculty == Faculties.values()[i] && students.get(k).course == j) {
                        System.out.println(students.get(k).toString());
                    }
                }
            }
        }
        System.out.println();
    }

    // Вывод списка студентов старше заданного года.
    private static void filterByBirthday(ArrayList<Student> students) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        System.out.print("Введите год: ");
        int year = consoleInputChoice.nextInt();
        printHeader();
        for (int i = 0; i < students.size(); i++) {
            int yearOfStudentBirthday = Integer.valueOf(dateFormat.format(students.get(i).birthday.getTime()));
            if (yearOfStudentBirthday > year) {
                System.out.println(students.get(i).toString());
            }
        }
        System.out.println();
    }

    // Вывод списка студентов выбранной учебной группы.
    private static void filterByGroup(ArrayList<Student> students, ArrayList<Group> groups) {
        System.out.println("Выберите группу: ");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + " - " + groups.get(i).groupId);
        }
        System.out.print("Ваш выбор: ");
        int indexOfChoice = consoleInputChoice.nextInt() - 1;
        printHeader();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).group.groupId == groups.get(indexOfChoice).groupId) {
                System.out.println(students.get(i).toString());
            }
        }
        System.out.println();
    }

    private static void printHeader() {
        String header = String.format("%-19s%-15s%-15s%-15s%-18s%-35s%-35s%-9s%-9s",
                "Студ. билет №", "Фамилия", "Имя", "Отчество",
                "Дата рождения", "Адрес", "Факультет", "Курс", "Группа");
        String separatorHorizontalLine = "-";
        while (separatorHorizontalLine.length() < 168) {
            separatorHorizontalLine += "-";
        }
        System.out.println(header + "\n" + separatorHorizontalLine);
    }

    private static void initializeGroupsArray(ArrayList<Group> groups) {
        groups.add(0, new Group(GroupsEnum.IT, "13ИТ-1"));
        groups.add(1, new Group(GroupsEnum.FINANCE, "13Ф-1"));
        groups.add(2, new Group(GroupsEnum.ACCOUNTING, "13БУ-2"));
    }

    private static void initializeAddressesArray(ArrayList<Student.Address> addresses) {
        addresses.add(0, new Student.Address("г. Речица, ул. Ленина, 52а", "+375 23 406-90-95"));
        addresses.add(1, new Student.Address("г.Речица, ул. Советская, 93", "8 (029) 555 00 49"));
        addresses.add(2, new Student.Address("г.Речица, ул. Снежкова, 16а", "(+375 29) 388-73-03"));
        addresses.add(3, new Student.Address("г. Речица, ул. Снежкова, 29", "+375 23 409-62-91"));
        addresses.add(4, new Student.Address("г. Речица, ул. Строителей, 2-68", "+375 29 147-18-31"));
        addresses.add(5, new Student.Address("г. Речица, ул. Урицкого,19а", "+375 44 538 94 25"));
        addresses.add(6, new Student.Address("г. Речица, ул.Ленина, 76", "+ 375 17 215-61-15"));
        addresses.add(7, new Student.Address("г. Речица, ул. Луначарского, 7", "+375 23 407-93-47"));
        addresses.add(8, new Student.Address("г. Речица, ул. Св. шоссе, 5А", "+375 23 409-62-77"));
        addresses.add(9, new Student.Address("г. Речица, ул. Советская, 51", "+375 23 409-90-13"));
    }

    // Грубая инициализация массива студентов.
    private static void initializeStudentsArray(ArrayList<Group> groups,
                                                ArrayList<Student> students,
                                                ArrayList<Student.Address> addresses) {
        students.add(0, new Student(344255, "Грицок", "Александр", "Евгеньевич",
                new GregorianCalendar(1996, Calendar.JULY, 23).getTime(),
                addresses.get(0), Faculties.BANKING, 4, groups.get(0)));
        students.add(1, new Student(234876, "Попов", "Виталий", "Павлович",
                new GregorianCalendar(1995, Calendar.FEBRUARY, 11).getTime(),
                addresses.get(1), Faculties.BANKING, 4, groups.get(0)));
        students.add(2, new Student(333222, "Иванов", "Иван", "Иванович",
                new GregorianCalendar(1997, Calendar.JANUARY, 7).getTime(),
                addresses.get(2), Faculties.ECONOMICAL, 4, groups.get(0)));
        students.add(3, new Student(101738, "Сидоров", "Николай", "Анатольевич",
                new GregorianCalendar(1996, Calendar.MAY, 16).getTime(),
                addresses.get(3), Faculties.BANKING, 3, groups.get(0)));
        students.add(4, new Student(168900, "Петров", "Юрий", "Владимирович",
                new GregorianCalendar(1995, Calendar.DECEMBER, 1).getTime(),
                addresses.get(4), Faculties.ECONOMICAL, 3, groups.get(1)));
        students.add(5, new Student(432899, "Мороз", "Александр", "Сергеевич",
                new GregorianCalendar(1998, Calendar.APRIL, 28).getTime(),
                addresses.get(5), Faculties.ECONOMICAL, 2, groups.get(1)));
        students.add(6, new Student(301201, "Зайцева", "Анастасия", "Романовна",
                new GregorianCalendar(2001, Calendar.AUGUST, 20).getTime(),
                addresses.get(6), Faculties.ECONOMICAL, 2, groups.get(1)));
        students.add(7, new Student(345555, "Семенова", "Юлия", "Владиславовна",
                new GregorianCalendar(1992, Calendar.OCTOBER, 15).getTime(),
                addresses.get(7), Faculties.ECONOMICAL, 2, groups.get(2)));
        students.add(8, new Student(809456, "Козлова", "Мария", "Викторовна",
                new GregorianCalendar(1999, Calendar.NOVEMBER, 3).getTime(),
                addresses.get(8), Faculties.BIOTECHNOLOGICAL, 2, groups.get(2)));
        students.add(9, new Student(100000, "Степанова", "Дарья", "Максимовна",
                new GregorianCalendar(2000, Calendar.MARCH, 6).getTime(),
                addresses.get(9), Faculties.BIOTECHNOLOGICAL, 1, groups.get(2)));
    }


}
