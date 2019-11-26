package errorandexceptions;

import errorandexceptions.exceptions.GradeValueIsOutOfBoundsException;
import errorandexceptions.exceptions.GroupHasNoStudentsException;
import errorandexceptions.exceptions.StudentHasNoSubjectsException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Group {
    static Logger logger = Logger.getLogger(Group.class.getName());
    private static Random random = new Random();
    private int id;
    private List<Student> students;
    private List<Subject> subjects;

    public Group(int id) {
        this.id = id;
        this.subjects = initializeSubjects();
        try {
            this.students = initializeStudents();
        } catch (GroupHasNoStudentsException e) {
            logger.log(Level.WARNING, e.getMessage());
            this.students.add(getStudentDefault());
        }
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Group id = " + id + printStudents();
    }

    public List<Student> initializeStudents() throws GroupHasNoStudentsException {
        List<Student> studentsOfThisGroup = new ArrayList<>();
        int numberOfStudents = random.nextInt(7) + 1;
        if (numberOfStudents == 0) {
            throw new GroupHasNoStudentsException("No students added to the group!");
        } else {
            for (int i = 0; i < numberOfStudents; i++) {
                try {
                    studentsOfThisGroup.add(new Student(addRandomGradesToEachSubject()));
                } catch (GradeValueIsOutOfBoundsException | StudentHasNoSubjectsException e) {
                    logger.log(Level.WARNING, e.getMessage());
                    studentsOfThisGroup.clear();
                    studentsOfThisGroup.add(getStudentDefault());
                }
            }
            return studentsOfThisGroup;
        }
    }

    public List<Subject> initializeSubjects() {
        ArrayList<Subject> subjectsOfThisGroup = new ArrayList<>();
        int numberOfSubjects = random.nextInt(4) + 1;
        for (int i = 0; i < numberOfSubjects; i++) {
            subjectsOfThisGroup.add(Subject.values()[i]);
        }
        return subjectsOfThisGroup;
    }

    public Map<Subject, List<Integer>> addRandomGradesToEachSubject() throws GradeValueIsOutOfBoundsException,
            StudentHasNoSubjectsException {
        Map<Subject, List<Integer>> randomGrades = new EnumMap<>(Subject.class);
        List<Integer> randomGradesValues = new ArrayList<>();
        for (Subject subject : subjects) {
            randomGradesValues.clear();
            int numberOfGrades = random.nextInt(4) + 1;
            if (numberOfGrades == 0) {
                throw new StudentHasNoSubjectsException("No subjects added to student's grades!");
            } else {
                for (int i = 0; i < numberOfGrades; i++) {
                    int randomGrade = random.nextInt(10) + 1;
                    if (randomGrade <= 10 && randomGrade >= 1)
                        randomGradesValues.add(randomGrade);
                    else {
                        throw new GradeValueIsOutOfBoundsException("Grade is not in [1:10] range!");
                    }
                }
                randomGrades.put(subject, randomGradesValues);
            }
        }
        return randomGrades;
    }

    public double getAverageGradeAtSubject(Subject subject) {
        int sumOfGradesOfAllStudentsOfGroupAtSubject = 0;
        int numberOfGradesOfAllStudentsOfGroupAtSubject = 0;
        for (Student student : students) {
            for (Map.Entry<Subject, List<Integer>> pair : student.getGrades().entrySet()) {
                if (pair.getKey().equals(subject)) {
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        sumOfGradesOfAllStudentsOfGroupAtSubject += pair.getValue().get(i);
                        numberOfGradesOfAllStudentsOfGroupAtSubject++;
                    }
                }
            }
        }
        return numberOfGradesOfAllStudentsOfGroupAtSubject != 0 ?
                sumOfGradesOfAllStudentsOfGroupAtSubject / (double) numberOfGradesOfAllStudentsOfGroupAtSubject : 0;
    }

    public int getSumOfGradesAtSubject(Subject chosenSubject) {
        int sumOfGradesAtSubject = 0;
        for (Student student : students) {
            for (Map.Entry<Subject, List<Integer>> pair : student.getGrades().entrySet()) {
                if (pair.getKey().equals(chosenSubject)) {
                    Iterator<Integer> iterator = pair.getValue().iterator();
                    while (iterator.hasNext()) {
                        sumOfGradesAtSubject += iterator.next();
                    }
                }
            }
        }
        return sumOfGradesAtSubject;
    }

    public String printStudents() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append("\n").append("   ").append(iterator.next().toString());
        }
        return stringBuilder.toString();
    }

    private Student getStudentDefault() {
        Map<Subject, List<Integer>> gradesDefault = new EnumMap<>(Subject.class);
        gradesDefault.put(subjects.get(0), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        return new Student(gradesDefault);
    }
}
