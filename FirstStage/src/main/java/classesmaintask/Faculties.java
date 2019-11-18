package classesmaintask;

public enum Faculties {
    BANKING("Факультет банковского дела"),
    ECONOMICAL("Экономический факультет"),
    BIOTECHNOLOGICAL("Биотехнологический факультет");
    public String facultyName;

    Faculties(String facultyName) {
        this.facultyName = facultyName;
    }
}
