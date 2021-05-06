package by.jwd.task6.fleet;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractAircraftModel implements Serializable {

    /**
     * Aircraft flight performance comparators.
     */
    public final static Comparator<AbstractAircraftModel> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.CRUISING_SPEED_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.FUEL_CONSUMPTION_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.MAX_ATTITUDE_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> MAX_RANGE_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.RANGE_COMPARATOR);

    /**
     * Aircraft size comparators.
     */
    public final static Comparator<AbstractAircraftModel> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.FUSELAGE_LENGTH_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.FUSELAGE_WIDTH_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_HEIGHT_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_LENGTH_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_WIDTH_COMPARATOR);

    /**
     * Aircraft weight comparators.
     */
    public final static Comparator<AbstractAircraftModel> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.EMPTY_WEIGHT_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.FUEL_CAPACITY_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.LANDING_WEIGHT_COMPARATOR);
    public final static Comparator<AbstractAircraftModel> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.TAKEOFF_WEIGHT_COMPARATOR);

    final static Predicate<Float> FINITE_POSITIVE_PREDICATE = (number) -> number > 0 && Float.isFinite(number);

    private final static String NULL_AIRCRAFT_PERFORMANCE_MESSAGE = "Aircraft performance cannot be null.";
    private final static String NULL_AIRCRAFT_SIZE_MESSAGE = "Aircraft size cannot be null.";
    private final static String NULL_AIRCRAFT_WEIGHT_MESSAGE = "Aircraft weight cannot be null.";
    private final static String NULL_REGISTRATION_CODE_MESSAGE = "Registration code cannot be null.";

    private AircraftPerformance aircraftPerformance;
    private AircraftSize aircraftSize;
    private AircraftWeight aircraftWeight;

    private String registrationCode;

    public AbstractAircraftModel(AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                                 AircraftWeight aircraftWeight, String registrationCode)
            throws IllegalArgumentException {
        ValidationHelper.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        ValidationHelper.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        ValidationHelper.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        ValidationHelper.validateArgument(registrationCode, Objects::nonNull, NULL_REGISTRATION_CODE_MESSAGE);
        this.aircraftPerformance = aircraftPerformance;
        this.aircraftSize = aircraftSize;
        this.aircraftWeight = aircraftWeight;
        this.registrationCode = registrationCode;
    }

    public AbstractAircraftModel() {
        aircraftPerformance = new AircraftPerformance();
        aircraftSize = new AircraftSize();
        registrationCode = "";
    }

    public AircraftPerformance getAircraftPerformance() {
        return aircraftPerformance;
    }

    public void setAircraftPerformance(AircraftPerformance aircraftPerformance) {
        ValidationHelper.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        this.aircraftPerformance = aircraftPerformance;
    }

    public AircraftSize getAircraftSize() {
        return aircraftSize;
    }

    public void setAircraftSize(AircraftSize aircraftSize) {
        ValidationHelper.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        this.aircraftSize = aircraftSize;
    }

    public AircraftWeight getAircraftWeight() {
        return aircraftWeight;
    }

    public void setAircraftWeight(AircraftWeight aircraftWeight) {
        ValidationHelper.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        this.aircraftWeight = aircraftWeight;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) throws IllegalArgumentException {
        ValidationHelper.validateArgument(registrationCode, Objects::nonNull, NULL_REGISTRATION_CODE_MESSAGE);
        this.registrationCode = registrationCode;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        int hashMultiplier = 31;
        int shiftRange = Integer.SIZE / 2;
        hash = hash * hashMultiplier + (aircraftPerformance.hashCode() >> shiftRange);
        hash = hash * hashMultiplier + (aircraftSize.hashCode() >> shiftRange);
        hash = hash * hashMultiplier + (aircraftWeight.hashCode() >> shiftRange);
        hash = hash * hashMultiplier + (registrationCode.hashCode() >> shiftRange);
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
        AbstractAircraftModel that = (AbstractAircraftModel) o;
        return aircraftPerformance.equals(that.aircraftPerformance) && aircraftSize.equals(that.aircraftSize)
               && aircraftWeight.equals(that.aircraftWeight) && registrationCode.equals(that.registrationCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "registrationCode='" + registrationCode + '\'' +
               ", aircraftPerformance=" + aircraftPerformance +
               ", aircraftSize=" + aircraftSize +
               ", aircraftWeight=" + aircraftWeight +
               '}';
    }
}
