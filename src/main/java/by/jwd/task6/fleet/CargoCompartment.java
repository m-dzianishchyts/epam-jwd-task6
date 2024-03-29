package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;
import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;

public class CargoCompartment implements Serializable {

    /**
     * Cargo compartment comparators.
     */
    public static final Comparator<CargoCompartment> HATCH_HEIGHT_COMPARATOR =
            Comparator.comparing(CargoCompartment::getHatchHeight);
    public static final Comparator<CargoCompartment> HATCH_WIDTH_COMPARATOR =
            Comparator.comparing(CargoCompartment::getHatchWidth);
    public static final Comparator<CargoCompartment> TOTAL_AREA_COMPARATOR =
            Comparator.comparing(CargoCompartment::calculateTotalArea);
    public static final Comparator<CargoCompartment> TOTAL_HEIGHT_COMPARATOR =
            Comparator.comparing(CargoCompartment::getTotalHeight);
    public static final Comparator<CargoCompartment> TOTAL_LENGTH_COMPARATOR =
            Comparator.comparing(CargoCompartment::getTotalLength);
    public static final Comparator<CargoCompartment> TOTAL_VOLUME_COMPARATOR =
            Comparator.comparing(CargoCompartment::calculateTotalVolume);
    public static final Comparator<CargoCompartment> TOTAL_WIDTH_COMPARATOR =
            Comparator.comparing(CargoCompartment::getTotalWidth);

    /**
     * Cargo compartment exception messages.
     */
    private static final String INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE =
            "Cargo compartment property must be finite and positive.";

    private static final long serialVersionUID = -1035749343471924874L;

    private float hatchHeight;
    private float hatchWidth;
    private float totalHeight;
    private float totalLength;
    private float totalWidth;

    public CargoCompartment(float totalHeight, float totalLength, float totalWidth,
                            float hatchHeight, float hatchWidth) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalHeight)
              || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalLength)
              || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalWidth)
              || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(hatchHeight)
              || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(hatchWidth)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.totalHeight = totalHeight;
        this.totalLength = totalLength;
        this.totalWidth = totalWidth;
        this.hatchHeight = hatchHeight;
        this.hatchWidth = hatchWidth;
    }

    public CargoCompartment() {
    }

    public float calculateTotalVolume() {
        float bayVolume = totalHeight * totalLength * totalWidth;
        return bayVolume;
    }

    public float calculateTotalArea() {
        float bayArea = totalWidth * totalLength;
        return bayArea;
    }

    public float getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(float totalHeight) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalHeight)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.totalHeight = totalHeight;
    }

    public float getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(float totalLength) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalLength)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.totalLength = totalLength;
    }

    public float getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(float totalWidth) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(totalWidth)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.totalWidth = totalWidth;
    }

    public float getHatchHeight() {
        return hatchHeight;
    }

    public void setHatchHeight(float hatchHeight) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(hatchHeight)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.hatchHeight = hatchHeight;
    }

    public float getHatchWidth() {
        return hatchWidth;
    }

    public void setHatchWidth(float hatchWidth) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(hatchWidth)) {
            throw new ArgumentValidationException(INVALID_CARGO_COMPARTMENT_PROPERTY_MESSAGE);
        }
        this.hatchWidth = hatchWidth;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(totalHeight, totalLength, totalWidth, hatchHeight, hatchWidth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CargoCompartment that = (CargoCompartment) o;
        return that.totalHeight == totalHeight
               && that.totalLength == totalLength
               && that.totalWidth == totalWidth
               && that.hatchHeight == hatchHeight
               && that.hatchWidth == hatchWidth;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "bayHeight=" + totalHeight +
               ", bayLength=" + totalLength +
               ", bayWidth=" + totalWidth +
               ", hatchHeight=" + hatchHeight +
               ", hatchWidth=" + hatchWidth +
               '}';
    }
}
