package errorandexceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
    private int id;
    private List<Student> students;
    private List<Subject> subjects;
    private Faculty faculty;
    private static Random random = new Random();

    public Group(int id, Faculty faculty) {
        this.id = id;
        this.students = initializeStudents();
        this.subjects = initializeSubjects();
        this.faculty = faculty;
    }

    public ArrayList<Student> initializeStudents() {
        ArrayList<Student> studentsOfThisGroup = new ArrayList<Student>();
        int numberOfStudents = random.nextInt(11);
        for (int i = 0; i < numberOfStudents; i++) {
            studentsOfThisGroup.add(new Student(this));
        }
        return studentsOfThisGroup;
    }

    public ArrayList<Subject> initializeSubjects(){
        ArrayList<Subject> subjectsOfThisGroup = new ArrayList<Subject>();
        int numberOfSubjects = random.nextInt(5);
        while(numberOfSubjects == 0)
            numberOfSubjects = random.nextInt(5);
        for (int i = 0; i < numberOfSubjects; i++) {
            subjectsOfThisGroup.add(Subject.values()[i]);
        }
        return subjectsOfThisGroup;
    }
    
}
