package errorandexceptions;

import errorandexceptions.exceptions.FacultyHasNoGroupsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Faculty {
    static Logger logger = Logger.getLogger(Faculty.class.getName());
    private static Random random = new Random();
    private String name;
    private List<Group> groups;

    public Faculty(String name) {
        this.name = name;
        try {
            this.groups = initializeGroups();
        } catch (FacultyHasNoGroupsException e) {
            logger.log(Level.WARNING, e.getMessage());
            Group groupDefault = new Group(1);
            this.groups.add(groupDefault);
        }
    }

    public String getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return "Faculty name = " + name + printGroups();
    }

    public List<Group> initializeGroups() throws FacultyHasNoGroupsException {
        ArrayList<Group> groupsOfThisFaculty = new ArrayList<>();
        int numberOfGroups = random.nextInt(5) + 1;
        if (numberOfGroups == 0) {
            throw new FacultyHasNoGroupsException("No groups added to the faculty!");
        } else {
            for (int i = 0; i < numberOfGroups; i++) {
                groupsOfThisFaculty.add(new Group(i + 1));
            }
            return groupsOfThisFaculty;
        }
    }

    public String printGroups() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append("\n  ").append(iterator.next().toString());
        }
        return stringBuilder.toString();
    }
}
