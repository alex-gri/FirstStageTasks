package errorandexceptions.exceptions;

public class GradeValueIsOutOfBoundsException extends Exception {
    public GradeValueIsOutOfBoundsException() {
    }

    public GradeValueIsOutOfBoundsException(String message) {
        super(message);
    }

    public GradeValueIsOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeValueIsOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
