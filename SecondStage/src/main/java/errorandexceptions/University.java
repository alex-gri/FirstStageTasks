package errorandexceptions;

import errorandexceptions.exceptions.UniversityHasNoFacultiesException;

import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class University {
    static Logger logger = Logger.getLogger(University.class.getName());
    private static Random random = new Random();
    private String name;
    private List<Faculty> faculties;

    public University(String name) {
        this.name = name;
        try {
            this.faculties = initializeFaculties();
        } catch (UniversityHasNoFacultiesException e) {

            Faculty facultyDefault = new Faculty("DEFAULT_FACULTY");
            this.faculties.add(facultyDefault);
        }
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public String toString() {
        return "University name = " + name + printFaculties();
    }

    public List<Faculty> initializeFaculties() throws UniversityHasNoFacultiesException {
        ArrayList<Faculty> facultiesOfThisUniversity = new ArrayList<>();
        int numberOfFaculties = random.nextInt(2) + 1;
        if (numberOfFaculties == 0) {
            throw new UniversityHasNoFacultiesException("No faculties added to the university!");
        } else {
            for (int i = 0; i < numberOfFaculties; i++) {
                facultiesOfThisUniversity.add(new Faculty(Month.of(i + 1).name()));
            }
            return facultiesOfThisUniversity;
        }
    }

    public String printFaculties() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Faculty> iterator = faculties.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            stringBuilder.append("\n").append(index).append(" - ").append(iterator.next().toString()).append(" ");
            index++;
        }
        return stringBuilder.toString();
    }

    public void printFacultiesNames() {
        for (int i = 0; i < faculties.size(); i++) {
            String facultyNameLine = (i + 1) + " - " + faculties.get(i).getName();
            logger.log(Level.SEVERE, facultyNameLine);
        }
    }
}
