package by.jwd.task6.fleet;

import java.util.Objects;
import java.util.function.Predicate;

public final class ValidationHelper {

    private ValidationHelper() {
    }

    public static <T> void validateArgument(T argument, Predicate<? super T> validity, String message)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(validity);
        Objects.requireNonNull(message);
        if (!validity.test(argument)) {
            throw new IllegalArgumentException(message);
        }
    }
}
