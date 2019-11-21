package errorandexceptions;

import errorandexceptions.exceptions.UniversityHasNoFacultiesException;

import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class University {
    private String name;
    private List<Faculty> faculties;
    private static Random random = new Random();

    public University(String name) {
        this.name = name;
        try {
            this.faculties = initializeFaculties();
        } catch (UniversityHasNoFacultiesException e) {
            e.printStackTrace();
            Faculty facultyDefault = new Faculty("DEFAULT_FACULTY");
            this.faculties.add(facultyDefault);
        }
    }

    public List<Faculty> initializeFaculties() throws UniversityHasNoFacultiesException {
        ArrayList<Faculty> facultiesOfThisUniversity = new ArrayList<>();
        int numberOfFaculties = random.nextInt(2);
        while(numberOfFaculties==0)
            numberOfFaculties = random.nextInt(2);
        if(numberOfFaculties == 0){
            throw new UniversityHasNoFacultiesException("No faculties added to the university!");
        }else{
            for (int i = 0; i < numberOfFaculties; i++) {
                facultiesOfThisUniversity.add(new Faculty(Month.of(i+1).name()));
            }
            return facultiesOfThisUniversity;
        }
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public String printFaculties(){
        StringBuilder bld = new StringBuilder();
        Iterator<Faculty> iterator=faculties.iterator();
        int index = 1;
        while(iterator.hasNext()){
            bld.append("\n").append(index).append(" - ").append(iterator.next().toString()).append(" ");
            index++;
        }
        return bld.toString();
    }

    public void printFacultiesNames(){
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i+1) + " - " + faculties.get(i).getName());
        }
    }

    @Override
    public String toString() {
        return "University " +
                "name=" + name +
                printFaculties();
    }
}
