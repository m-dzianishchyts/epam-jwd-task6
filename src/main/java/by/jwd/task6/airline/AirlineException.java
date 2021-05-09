package by.jwd.task6.airline;

public class AirlineException extends Exception {

    public AirlineException() {
    }

    public AirlineException(String message) {
        super(message);
    }

    public AirlineException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirlineException(Throwable cause) {
        super(cause);
    }
}
