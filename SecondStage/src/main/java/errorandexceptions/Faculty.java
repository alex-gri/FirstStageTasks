package errorandexceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Faculty {
    private String name;
    private List<Group> groups;
    private static Random random = new Random();

    public Faculty(String name) {
        this.name = name;
        this.groups = initializeGroups();
    }

    public List<Group> initializeGroups(){
        ArrayList<Group> groupsOfThisFaculty = new ArrayList<Group>();
        int numberOfGroups = random.nextInt(8);
        while(numberOfGroups == 0)
            numberOfGroups = random.nextInt(8);
        for (int i = 0; i < numberOfGroups; i++) {
            groupsOfThisFaculty.add(new Group(i+1,this));
        }
        return groupsOfThisFaculty;
    }

    public String getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
