package errorandexceptions;

import java.util.*;

public class Student {
    private String initial;
    private Map<Subject, List<Integer>> grades;
    private static Random random = new Random();

    public Student(Map<Subject, List<Integer>> grades) {
        // Student's name is represented like initial (string of 3 uppercase characters)
        this.initial = getRandomInitial();
        this.grades = grades;
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

    public String printGrades() {
        StringBuilder bld = new StringBuilder();
        for (Map.Entry<Subject, List<Integer>> pair : this.grades.entrySet()) {
            bld.append(" | ").append(pair.getKey().name()).append(":");
            Iterator<Integer> iterator=pair.getValue().iterator();
            int index = 1;
            while(iterator.hasNext()){
                bld.append("  ").append(iterator.next());
                index++;
            }
        }
        return bld.toString();
    }

    @Override
    public String toString() {
        return   "student initial=" + initial +
                ", grades: " + printGrades();
    }
}

