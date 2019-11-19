package errorandexceptions;

import java.util.ArrayList;
import java.util.Iterator;
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
        ArrayList<Group> groupsOfThisFaculty = new ArrayList<>();
        int numberOfGroups = random.nextInt(8);
        while(numberOfGroups == 0)
            numberOfGroups = random.nextInt(8);
        for (int i = 0; i < numberOfGroups; i++) {
            groupsOfThisFaculty.add(new Group(i+1));
        }
        return groupsOfThisFaculty;
    }

    public String getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String printGroups(){
        StringBuilder bld = new StringBuilder();
        Iterator<Group> iterator=groups.iterator();
        while(iterator.hasNext()){
            bld.append("\n").append(iterator.next().toString());
        }
        return bld.toString();
    }

    @Override
    public String toString() {
        return "Faculty " +
                "name='" + name +
                printGroups();
    }
}
