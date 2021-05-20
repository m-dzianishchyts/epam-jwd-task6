package by.jwd.task6.util;

import java.util.function.Predicate;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static final Predicate<Float> FINITE_POSITIVE_PREDICATE = number -> number > 0 && Float.isFinite(number);
}
