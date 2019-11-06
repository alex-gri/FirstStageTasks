package classesmaintask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {

    public final int id;
    public String lastName;
    public String name;
    public String fatherName;
    public Date birthday;

    public static class Address {
        private String address;
        private String phoneNumber;

        public Address(String address, String phoneNumber) {
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public Address address;

    public Faculties faculty;
    public int course;
    public Group group;

    // Конструктор для всех полей.
    public Student(int id, String lastName, String name,
                   String fatherName, Date birthday, Address address,
                   Faculties faculty, int course, Group group) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthday = birthday;
        this.address = address;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    // Конструктор для заполнения основной информации.
    public Student(int id, String lastName, String name, String fatherName) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.fatherName = fatherName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    // Здесь еще можно поработать с табуляцией, через выделение N позиций для каждого поля.
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("%-19s%-15s%-15s%-15s%-18s%-35s%-35s%-9s%-9s", id, lastName, name, fatherName,
                dateFormat.format(birthday.getTime()), address.address, faculty.facultyName, course, group.groupId);
    }
}
