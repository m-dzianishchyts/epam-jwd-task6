package by.jwd.task6.util;

public class ArgumentValidationException extends Exception {

    private static final long serialVersionUID = 8071752468096765907L;

    public ArgumentValidationException() {
    }

    public ArgumentValidationException(String message) {
        super(message);
    }

    public ArgumentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentValidationException(Throwable cause) {
        super(cause);
    }
}
