package errorandexceptions;

import errorandexceptions.exceptions.FacultyHasNoGroupsException;

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
        try {
            this.groups = initializeGroups();
        } catch (FacultyHasNoGroupsException e) {
            e.printStackTrace();
            Group groupDefault = new Group(1);
            this.groups.add(groupDefault);
        }
    }

    public List<Group> initializeGroups() throws FacultyHasNoGroupsException {
        ArrayList<Group> groupsOfThisFaculty = new ArrayList<>();
        int numberOfGroups = random.nextInt(5);
        while(numberOfGroups==0)
            numberOfGroups = random.nextInt(5);
        if(numberOfGroups == 0){
            throw new FacultyHasNoGroupsException("No groups added to the faculty!");
        }else{
            for (int i = 0; i < numberOfGroups; i++) {
                groupsOfThisFaculty.add(new Group(i+1));
            }
            return groupsOfThisFaculty;
        }
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
            bld.append("\n  ").append(iterator.next().toString());
        }
        return bld.toString();
    }

    @Override
    public String toString() {
        return "Faculty " +
                "name=" + name +
                printGroups();
    }
}
