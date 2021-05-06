package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

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

    /**
     * Aircraft model name comparator.
     */
    public final static Comparator<AbstractAircraftModel> MODEL_NAME_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getModelName);

    /**
     * Predicate for validating aircraft parameters.
     */
    protected final static Predicate<Float> FINITE_POSITIVE_PREDICATE =
            (number) -> number > 0 && Float.isFinite(number);

    /**
     * Exception messages.
     */
    protected final static String NULL_AIRCRAFT_PERFORMANCE_MESSAGE = "Aircraft performance cannot be null.";
    protected final static String NULL_AIRCRAFT_SIZE_MESSAGE = "Aircraft size cannot be null.";
    protected final static String NULL_AIRCRAFT_WEIGHT_MESSAGE = "Aircraft weight cannot be null.";
    protected final static String NULL_MODEL_NAME_MESSAGE = "Model name cannot be null.";

    /**
     * Package-common exception messages.
     */
    protected final static String INVALID_SIZE_PROPERTY_MESSAGE = "Size property must be finite and positive.";
    protected final static String INVALID_WEIGHT_PROPERTY_MESSAGE = "Weight property must be finite and positive.";
    protected final static String INVALID_FLIGHT_PROPERTY_MESSAGE = "Flight property must be finite and positive.";

    protected AircraftPerformance aircraftPerformance;
    protected AircraftSize aircraftSize;
    protected AircraftWeight aircraftWeight;
    protected String modelName;

    public AbstractAircraftModel(AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                                 AircraftWeight aircraftWeight, String modelName)
            throws IllegalArgumentException {
        ValidationHelper.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        ValidationHelper.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        ValidationHelper.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        ValidationHelper.validateArgument(modelName, Objects::nonNull, NULL_MODEL_NAME_MESSAGE);
        this.aircraftPerformance = aircraftPerformance;
        this.aircraftSize = aircraftSize;
        this.aircraftWeight = aircraftWeight;
        this.modelName = modelName;
    }

    public AbstractAircraftModel() {
        aircraftPerformance = new AircraftPerformance();
        aircraftSize = new AircraftSize();
        aircraftWeight = new AircraftWeight();
        modelName = "";
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) throws IllegalArgumentException {
        ValidationHelper.validateArgument(modelName, Objects::nonNull, NULL_MODEL_NAME_MESSAGE);
        this.modelName = modelName;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(aircraftPerformance, aircraftSize, aircraftWeight, modelName);
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
               && aircraftWeight.equals(that.aircraftWeight) && modelName.equals(that.modelName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "modelName='" + modelName + '\'' +
               ", aircraftPerformance=" + aircraftPerformance +
               ", aircraftSize=" + aircraftSize +
               ", aircraftWeight=" + aircraftWeight +
               '}';
    }
}
