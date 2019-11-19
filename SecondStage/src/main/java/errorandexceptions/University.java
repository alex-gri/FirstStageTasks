package errorandexceptions;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class University {
    private String name;
    private List<Faculty> faculties;
    private static Random random = new Random();

    public University(String name) {
        this.name = name;
        this.faculties = initializeFaculties();
    }

    public List<Faculty> initializeFaculties(){
        ArrayList<Faculty> facultiesOfThisUniversity = new ArrayList<>();
        int numberOfFaculties = random.nextInt(11);
        while(numberOfFaculties == 0)
            numberOfFaculties = random.nextInt(12);
        for (int i = 0; i < numberOfFaculties; i++) {
            facultiesOfThisUniversity.add(new Faculty(Month.of(i+1).name()));
        }
        return facultiesOfThisUniversity;
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}
