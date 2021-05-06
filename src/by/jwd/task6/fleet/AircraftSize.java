package by.jwd.task6.fleet;

import java.io.Serializable;
import java.util.Comparator;

import static by.jwd.task6.fleet.AbstractAircraftModel.FINITE_POSITIVE_PREDICATE;

public class AircraftSize implements Serializable {

    public final static Comparator<AircraftSize> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(AircraftSize::getFuselageLength);
    public final static Comparator<AircraftSize> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(AircraftSize::getFuselageWidth);
    public final static Comparator<AircraftSize> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(AircraftSize::getOverallHeight);
    public final static Comparator<AircraftSize> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(AircraftSize::getOverallLength);
    public final static Comparator<AircraftSize> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(AircraftSize::getOverallWidth);

    private final static String INVALID_SIZE_PROPERTY_MESSAGE = "Size property must be finite and positive.";

    private float fuselageLength;
    private float fuselageWidth;
    private float overallHeight;
    private float overallLength;
    private float overallWidth;

    public AircraftSize(float fuselageLength, float fuselageWidth, float overallHeight, float overallLength,
                        float overallWidth) throws IllegalArgumentException {
        ValidationHelper.validateArgument(fuselageLength, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(fuselageWidth, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(overallHeight, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(overallLength, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(overallWidth, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.fuselageLength = fuselageLength;
        this.fuselageWidth = fuselageWidth;
        this.overallHeight = overallHeight;
        this.overallLength = overallLength;
        this.overallWidth = overallWidth;
    }

    public AircraftSize() {
    }

    public float getFuselageLength() {
        return fuselageLength;
    }

    public void setFuselageLength(float fuselageLength) {
        ValidationHelper.validateArgument(fuselageLength, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.fuselageLength = fuselageLength;
    }

    public float getFuselageWidth() {
        return fuselageWidth;
    }

    public void setFuselageWidth(float fuselageWidth) {
        ValidationHelper.validateArgument(fuselageWidth, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.fuselageWidth = fuselageWidth;
    }

    public float getOverallHeight() {
        return overallHeight;
    }

    public void setOverallHeight(float overallHeight) {
        ValidationHelper.validateArgument(overallHeight, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.overallHeight = overallHeight;
    }

    public float getOverallLength() {
        return overallLength;
    }

    public void setOverallLength(float overallLength) {
        ValidationHelper.validateArgument(overallLength, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.overallLength = overallLength;
    }

    public float getOverallWidth() {
        return overallWidth;
    }

    public void setOverallWidth(float overallWidth) {
        ValidationHelper.validateArgument(overallWidth, FINITE_POSITIVE_PREDICATE, INVALID_SIZE_PROPERTY_MESSAGE);
        this.overallWidth = overallWidth;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        int hashMultiplier = 31;
        int shiftRange = Integer.SIZE / 2;
        hash = hash * hashMultiplier + (Float.floatToRawIntBits(fuselageLength) >> shiftRange);
        hash = hash * hashMultiplier + (Float.floatToRawIntBits(fuselageWidth) >> shiftRange);
        hash = hash * hashMultiplier + (Float.floatToRawIntBits(overallHeight) >> shiftRange);
        hash = hash * hashMultiplier + (Float.floatToRawIntBits(overallLength) >> shiftRange);
        hash = hash * hashMultiplier + (Float.floatToRawIntBits(overallWidth) >> shiftRange);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AircraftSize that = (AircraftSize) o;
        return that.fuselageLength == fuselageLength
               && that.fuselageWidth == fuselageWidth
               && that.overallHeight == overallHeight
               && that.overallLength == overallLength
               && that.overallWidth == overallWidth;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "fuselageLength=" + fuselageLength +
               ", fuselageWidth=" + fuselageWidth +
               ", overallHeight=" + overallHeight +
               ", overallLength=" + overallLength +
               ", overallWidth=" + overallWidth +
               '}';
    }
}
