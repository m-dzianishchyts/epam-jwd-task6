package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

import java.io.Serializable;
import java.util.Comparator;

import static by.jwd.task6.fleet.AbstractAircraftModel.FINITE_POSITIVE_PREDICATE;
import static by.jwd.task6.fleet.AbstractAircraftModel.INVALID_FLIGHT_PROPERTY_MESSAGE;

public class AircraftPerformance implements Serializable {

    /**
     * Aircraft flight performance comparators.
     */
    public final static Comparator<AircraftPerformance> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getCruisingSpeed);
    public final static Comparator<AircraftPerformance> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getHourlyFuelConsumption);
    public final static Comparator<AircraftPerformance> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getMaxAttitude);
    public final static Comparator<AircraftPerformance> RANGE_COMPARATOR =
            Comparator.comparing(AircraftPerformance::getRange);

    private float cruisingSpeed;
    private float hourlyFuelConsumption;
    private float maxAttitude;
    private float range;

    public AircraftPerformance(float cruisingSpeed, float hourlyFuelConsumption, float maxAttitude, float range)
            throws IllegalArgumentException {
        ValidationHelper.validateArgument(cruisingSpeed, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(hourlyFuelConsumption, FINITE_POSITIVE_PREDICATE,
                                          INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(maxAttitude, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(range, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.cruisingSpeed = cruisingSpeed;
        this.hourlyFuelConsumption = hourlyFuelConsumption;
        this.maxAttitude = maxAttitude;
        this.range = range;
    }

    public AircraftPerformance() {
    }

    public float getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(float cruisingSpeed) {
        ValidationHelper.validateArgument(cruisingSpeed, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.cruisingSpeed = cruisingSpeed;
    }

    public float getMaxAttitude() {
        return maxAttitude;
    }

    public void setMaxAttitude(float maxAttitude) {
        ValidationHelper.validateArgument(maxAttitude, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.maxAttitude = maxAttitude;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        ValidationHelper.validateArgument(range, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.range = range;
    }

    public float getHourlyFuelConsumption() {
        return hourlyFuelConsumption;
    }

    public void setHourlyFuelConsumption(float hourlyFuelConsumption) {
        ValidationHelper.validateArgument(hourlyFuelConsumption, FINITE_POSITIVE_PREDICATE,
                                          INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.hourlyFuelConsumption = hourlyFuelConsumption;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(cruisingSpeed, hourlyFuelConsumption, maxAttitude, range);
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
               && that.hourlyFuelConsumption == hourlyFuelConsumption
               && that.maxAttitude == maxAttitude
               && that.range == range;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "cruisingSpeed=" + cruisingSpeed +
               ", hourlyFuelConsumption=" + hourlyFuelConsumption +
               ", maxAttitude=" + maxAttitude +
               ", maxRange=" + range +
               '}';
    }
}
