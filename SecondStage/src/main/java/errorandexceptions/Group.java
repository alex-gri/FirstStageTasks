package errorandexceptions;

import java.util.*;

public class Group {
    private int id;
    private List<Student> students;
    private List<Subject> subjects;
    private static Random random = new Random();

    public Group(int id) {
        this.id = id;
        this.subjects = initializeSubjects();
        this.students = initializeStudents();

    }

    public List<Student> initializeStudents() {
        ArrayList<Student> studentsOfThisGroup = new ArrayList<>();
        int numberOfStudents = random.nextInt(7);
        while(numberOfStudents == 0)
            numberOfStudents = random.nextInt(7);
        for (int i = 0; i < numberOfStudents; i++) {
            studentsOfThisGroup.add(new Student(addRandomGradesToEachSubject()));
        }
        return studentsOfThisGroup;
    }

    public List<Subject> initializeSubjects(){
        ArrayList<Subject> subjectsOfThisGroup = new ArrayList<>();
        int numberOfSubjects = random.nextInt(5);
        while(numberOfSubjects == 0)
            numberOfSubjects = random.nextInt(5);
        for (int i = 0; i < numberOfSubjects; i++) {
            subjectsOfThisGroup.add(Subject.values()[i]);
        }
        return subjectsOfThisGroup;
    }
    public Map<Subject, List<Integer>> addRandomGradesToEachSubject() {
        Map<Subject, List<Integer>> randomGrades = new EnumMap<>(Subject.class);
        List<Integer> randomGradesValues = new ArrayList<>();
        for (Subject subject : subjects) {
            randomGradesValues.clear();
            int numberOfGrades = random.nextInt(4);
            while(numberOfGrades == 0)
                numberOfGrades = random.nextInt(4);
            for (int i = 0; i < numberOfGrades; i++) {
                randomGradesValues.add(random.nextInt(10)+1);
            }
            randomGrades.put(subject, randomGradesValues);
        }
        return randomGrades;
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

    public double getAverageGradeAtSubject(Subject subject){
        int sumOfGradesOfAllStudentsOfGroupAtSubject = 0;
        int numberOfGradesOfAllStudentsOfGroupAtSubject = 0;
        for (Student student: students) {
            for (Map.Entry<Subject, List<Integer>> pair : student.getGrades().entrySet()) {
                if (pair.getKey().equals(subject)) {
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        sumOfGradesOfAllStudentsOfGroupAtSubject += pair.getValue().get(i);
                        numberOfGradesOfAllStudentsOfGroupAtSubject++;
                    }
                }
            }
        }
        if(numberOfGradesOfAllStudentsOfGroupAtSubject != 0){
        return (double)sumOfGradesOfAllStudentsOfGroupAtSubject / (double)numberOfGradesOfAllStudentsOfGroupAtSubject;
        }else{
            return 0;
        }
    }

    public String printStudents(){
        StringBuilder bld = new StringBuilder();
        Iterator<Student> iterator=students.iterator();
        while(iterator.hasNext()){
            bld.append("\n").append("   ").append(iterator.next().toString());
        }
        return bld.toString();
    }

    @Override
    public String toString() {
        return "Group " +
                "id=" + id +
                printStudents();
    }
}
