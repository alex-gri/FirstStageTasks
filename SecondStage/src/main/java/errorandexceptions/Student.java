package errorandexceptions;

import java.util.*;

public class Student {
    private String initial;
    private Map<Subject, List<Integer>> grades;
    private static Random random = new Random();
    public Student() {
        // Student's name is represented like initial (string of 3 uppercase characters)
        this.initial = getRandomInitial();
    }

    // Generates decimal ASCII value, converts it to character 3 times and concatenates.
    private String getRandomInitial() {
        return "" + (char) (random.nextInt((90 - 65) + 1) + 65)
                + (char) (random.nextInt((90 - 65) + 1) + 65)
                + (char) (random.nextInt((90 - 65) + 1) + 65);
    }

    public Map<Subject, List<Integer>> getGrades() {
        return grades;
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

    public String getInitial() {
        return initial;
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
        else{
            return 0;
        }
    }

    public void addRandomGradesToEachSubject() {
        List<Integer> randomGrades = new ArrayList<Integer>();
        for (Subject subject : Subject.values()) {
            int numberOfGrades = random.nextInt(8);
            for (int i = 0; i < numberOfGrades; i++) {
                randomGrades.add(random.nextInt(11));
            }
            addGrades(subject, randomGrades);
            randomGrades.clear();
        }
    }
}

