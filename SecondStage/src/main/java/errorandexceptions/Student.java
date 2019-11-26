package errorandexceptions;

import java.util.*;

public class Student {
    private static Random random = new Random();
    private String initial;
    private Map<Subject, List<Integer>> grades;

    public Student(Map<Subject, List<Integer>> grades) {
        // Student's name is represented like initial (string of 3 uppercase characters)
        this.initial = getRandomInitial();
        this.grades = grades;
    }

    public Map<Subject, List<Integer>> getGrades() {
        return grades;
    }

    public String getInitial() {
        return initial;
    }

    @Override
    public String toString() {
        return "student initial = " + initial + ", grades: " + printGrades();
    }

    // Generates decimal ASCII value, converts it to character 3 times and concatenates.
    private String getRandomInitial() {
        return "" + (char) (random.nextInt((90 - 65) + 1) + 65)
                + (char) (random.nextInt((90 - 65) + 1) + 65)
                + (char) (random.nextInt((90 - 65) + 1) + 65);
    }

    public double getAverageGradeOfAllSubjects() {
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
        return numberOfGradesOfAllSubjects != 0 ? sumOfAllGradesOfAllSubjects / (double) numberOfGradesOfAllSubjects : 0;
    }

    public String printGrades() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Subject, List<Integer>> pair : this.grades.entrySet()) {
            stringBuilder.append(" | ").append(pair.getKey().name()).append(":");
            Iterator<Integer> iterator = pair.getValue().iterator();
            while (iterator.hasNext()) {
                stringBuilder.append("  ").append(iterator.next());
            }
        }
        return stringBuilder.toString();
    }
}

