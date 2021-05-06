package by.jwd.task6.util;

public final class HashUtil {

    private static final int HASH_MULTIPLIER = 31;
    private static final int INITIAL_HASH = 17;

    private HashUtil() {
    }

    public static int hashFrom(Object... values) {
        if (values == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (Object value : values) {
            hash = hash * HASH_MULTIPLIER + value.hashCode();
        }
        return hash;
    }
}
