package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;
import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;

import static by.jwd.task6.fleet.Aircraft.INVALID_WEIGHT_PROPERTY_MESSAGE;

public class AircraftWeight implements Serializable {

    /**
     * Aircraft weight comparators.
     */
    public static final Comparator<AircraftWeight> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(AircraftWeight::getEmptyWeight);
    public static final Comparator<AircraftWeight> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(AircraftWeight::getFuelCapacity);
    public static final Comparator<AircraftWeight> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(AircraftWeight::getLandingWeight);
    public static final Comparator<AircraftWeight> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(AircraftWeight::getTakeoffWeight);

    private static final long serialVersionUID = 3935469395978153657L;

    private float emptyWeight;
    private float fuelCapacity;
    private float landingWeight;
    private float takeoffWeight;

    public AircraftWeight(float emptyWeight, float fuelCapacity, float landingWeight, float takeoffWeight)
            throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(emptyWeight)
            || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(fuelCapacity)
            || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(landingWeight)
            || !ValidationUtil.FINITE_POSITIVE_PREDICATE.test(takeoffWeight)) {
            throw new ArgumentValidationException(INVALID_WEIGHT_PROPERTY_MESSAGE);
        }
        this.emptyWeight = emptyWeight;
        this.fuelCapacity = fuelCapacity;
        this.landingWeight = landingWeight;
        this.takeoffWeight = takeoffWeight;
    }

    public AircraftWeight() {
    }

    public float getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(float emptyWeight) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(emptyWeight)) {
            throw new ArgumentValidationException(INVALID_WEIGHT_PROPERTY_MESSAGE);
        }
        this.emptyWeight = emptyWeight;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(float fuelCapacity) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(fuelCapacity)) {
            throw new ArgumentValidationException(INVALID_WEIGHT_PROPERTY_MESSAGE);
        }
        this.fuelCapacity = fuelCapacity;
    }

    public float getLandingWeight() {
        return landingWeight;
    }

    public void setLandingWeight(float landingWeight) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(landingWeight)) {
            throw new ArgumentValidationException(INVALID_WEIGHT_PROPERTY_MESSAGE);
        }
        this.landingWeight = landingWeight;
    }

    public float getTakeoffWeight() {
        return takeoffWeight;
    }

    public void setTakeoffWeight(float takeoffWeight) throws ArgumentValidationException {
        if (!ValidationUtil.FINITE_POSITIVE_PREDICATE.test(takeoffWeight)) {
            throw new ArgumentValidationException(INVALID_WEIGHT_PROPERTY_MESSAGE);
        }
        this.takeoffWeight = takeoffWeight;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(emptyWeight, fuelCapacity, landingWeight, takeoffWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AircraftWeight that = (AircraftWeight) o;
        return that.emptyWeight == emptyWeight
               && that.fuelCapacity == fuelCapacity
               && that.landingWeight == landingWeight
               && that.takeoffWeight == takeoffWeight;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "emptyWeight=" + emptyWeight +
               ", fuelCapacity=" + fuelCapacity +
               ", landingWeight=" + landingWeight +
               ", takeoffWeight=" + takeoffWeight +
               '}';
    }
}
