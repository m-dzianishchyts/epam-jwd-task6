package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;

import static by.jwd.task6.fleet.AbstractAircraftModel.FINITE_POSITIVE_PREDICATE;
import static by.jwd.task6.fleet.AbstractAircraftModel.INVALID_FLIGHT_PROPERTY_MESSAGE;

public class AircraftPerformance implements Serializable {

    /**
     * Aircraft flight performance comparators.
     */
    public static final Comparator<AircraftPerformance> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getCruisingSpeed);
    public static final Comparator<AircraftPerformance> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getFuelConsumption);
    public static final Comparator<AircraftPerformance> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getMaxAttitude);
    public static final Comparator<AircraftPerformance> RANGE_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getRange);

    private static final long serialVersionUID = 2308928128049295433L;

    private float cruisingSpeed;
    private float fuelConsumption;
    private float maxAttitude;
    private float range;

    public AircraftPerformance(float cruisingSpeed, float fuelConsumption, float maxAttitude, float range)
            throws IllegalArgumentException {
        ValidationUtil.validateArgument(cruisingSpeed, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationUtil.validateArgument(fuelConsumption, FINITE_POSITIVE_PREDICATE,
                                        INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationUtil.validateArgument(maxAttitude, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationUtil.validateArgument(range, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.cruisingSpeed = cruisingSpeed;
        this.fuelConsumption = fuelConsumption;
        this.maxAttitude = maxAttitude;
        this.range = range;
    }

    public AircraftPerformance() {
    }

    public float getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(float cruisingSpeed) throws IllegalArgumentException {
        ValidationUtil.validateArgument(cruisingSpeed, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.cruisingSpeed = cruisingSpeed;
    }

    public float getMaxAttitude() {
        return maxAttitude;
    }

    public void setMaxAttitude(float maxAttitude) throws IllegalArgumentException {
        ValidationUtil.validateArgument(maxAttitude, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.maxAttitude = maxAttitude;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) throws IllegalArgumentException {
        ValidationUtil.validateArgument(range, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.range = range;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) throws IllegalArgumentException {
        ValidationUtil.validateArgument(fuelConsumption, FINITE_POSITIVE_PREDICATE,
                                        INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(cruisingSpeed, fuelConsumption, maxAttitude, range);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AircraftPerformance that = (AircraftPerformance) o;
        return that.cruisingSpeed == cruisingSpeed
               && that.fuelConsumption == fuelConsumption
               && that.maxAttitude == maxAttitude
               && that.range == range;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "cruisingSpeed=" + cruisingSpeed +
               ", hourlyFuelConsumption=" + fuelConsumption +
               ", maxAttitude=" + maxAttitude +
               ", range=" + range +
               '}';
    }
}
