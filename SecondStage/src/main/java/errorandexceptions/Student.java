package errorandexceptions;

import java.util.*;

public class Student {
    private String name;
    private Map<Subject, List<Integer>> grades;
    private Group group;

    public Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public Map<Subject, List<Integer>> getGrades() {
        return grades;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public void setGrades(Map<Subject, List<Integer>> grades) {
        this.grades = new HashMap<Subject, List<Integer>>(grades);
    }

    public void addGrades(Subject subject, List<Integer> grades) {
        for (Map.Entry<Subject, List<Integer>> pair : this.grades.entrySet()) {
            if (pair.getKey().equals(subject)) {
                List<Integer> updatedGrades = new ArrayList<Integer>(pair.getValue());
                updatedGrades.addAll(grades);
                pair.setValue(updatedGrades);
            }
        }
    }

    public double averageGradeOfAllSubjects() {
        int sumOfAllGradesOfAllSubjects = 0;
        int numberOfGradesOfAllSubjects = 0;
        Iterator iterator = grades.values().iterator();
        while (iterator.hasNext()) {
            List<Integer> gradesOfOneSubject = (List<Integer>) iterator.next();
            Iterator<Integer> gradesOfOneSubjectIterator = gradesOfOneSubject.iterator();
            while (gradesOfOneSubjectIterator.hasNext()) {
                sumOfAllGradesOfAllSubjects += gradesOfOneSubjectIterator.next();
            }
            numberOfGradesOfAllSubjects += gradesOfOneSubject.size();
        }

        if (numberOfGradesOfAllSubjects != 0) {
            return (double) sumOfAllGradesOfAllSubjects / (double) numberOfGradesOfAllSubjects;
        }
    }
}
