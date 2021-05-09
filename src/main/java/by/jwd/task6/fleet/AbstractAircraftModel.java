package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractAircraftModel<F extends AircraftModelFeature> implements Serializable {

    /**
     * Aircraft flight performance comparators.
     */
    public static final Comparator<AbstractAircraftModel<?>> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.CRUISING_SPEED_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.FUEL_CONSUMPTION_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.MAX_ATTITUDE_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> MAX_RANGE_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftPerformance,
                                 AircraftPerformance.RANGE_COMPARATOR);

    /**
     * Aircraft size comparators.
     */
    public static final Comparator<AbstractAircraftModel<?>> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.FUSELAGE_LENGTH_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.FUSELAGE_WIDTH_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_HEIGHT_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_LENGTH_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftSize, AircraftSize.OVERALL_WIDTH_COMPARATOR);

    /**
     * Aircraft weight comparators.
     */
    public static final Comparator<AbstractAircraftModel<?>> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.EMPTY_WEIGHT_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.FUEL_CAPACITY_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.LANDING_WEIGHT_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<?>> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftWeight, AircraftWeight.TAKEOFF_WEIGHT_COMPARATOR);

    /**
     * Aircraft model own comparators.
     */
    public static final Comparator<AbstractAircraftModel<?>> ID_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getId);
    public static final Comparator<AbstractAircraftModel<?>> MODEL_NAME_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getModelName);
    public static final Comparator<AbstractAircraftModel<?>> MANUFACTURER_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getManufacturer);

    /**
     * Passenger aircraft model comparators.
     */
    public static final Comparator<AbstractAircraftModel<PassengerCompartment>> PASSENGER_CAPACITY_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 PassengerCompartment.PASSENGER_CAPACITY_COMPARATOR);

    /**
     * Cargo aircraft model comparators.
     */
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> HATCH_HEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.HATCH_HEIGHT_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> HATCH_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.HATCH_WIDTH_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> TOTAL_AREA_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.TOTAL_AREA_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> TOTAL_HEIGHT_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.TOTAL_HEIGHT_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> TOTAL_LENGTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.TOTAL_LENGTH_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> TOTAL_VOLUME_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.TOTAL_VOLUME_COMPARATOR);
    public static final Comparator<AbstractAircraftModel<CargoCompartment>> TOTAL_WIDTH_COMPARATOR =
            Comparator.comparing(AbstractAircraftModel::getAircraftModelFeature,
                                 CargoCompartment.TOTAL_WIDTH_COMPARATOR);

    /**
     * Predicate for validating aircraft parameters.
     */
    protected static final Predicate<Float> FINITE_POSITIVE_PREDICATE =
            (number) -> number > 0 && Float.isFinite(number);

    /**
     * Aircraft own exception messages.
     */
    protected static final String INVALID_ID_MESSAGE = "Aircraft model ID cannot be negative.";
    protected static final String NULL_AIRCRAFT_PERFORMANCE_MESSAGE = "Aircraft performance cannot be null.";
    protected static final String NULL_AIRCRAFT_SIZE_MESSAGE = "Aircraft size cannot be null.";
    protected static final String NULL_AIRCRAFT_WEIGHT_MESSAGE = "Aircraft weight cannot be null.";
    protected static final String NULL_MANUFACTURER_MESSAGE = "Manufacturer cannot be null.";
    protected static final String NULL_MODEL_NAME_MESSAGE = "Model name cannot be null.";
    protected static final String NULL_MODEL_FEATURE_MESSAGE = "Model feature cannot be null.";
    protected static final String NULL_MODEL_FEATURE_SUPPLIER_MESSAGE = "Model feature supplier cannot be null.";

    /**
     * Aircraft model properties exception messages.
     */
    protected static final String INVALID_SIZE_PROPERTY_MESSAGE = "Size property must be finite and positive.";
    protected static final String INVALID_WEIGHT_PROPERTY_MESSAGE = "Weight property must be finite and positive.";
    protected static final String INVALID_FLIGHT_PROPERTY_MESSAGE = "Flight property must be finite and positive.";

    private static final long serialVersionUID = -735779708391376136L;

    private AircraftPerformance aircraftPerformance;
    private AircraftSize aircraftSize;
    private AircraftWeight aircraftWeight;
    private int id;
    private String manufacturer;
    private String modelName;
    private F aircraftModelFeature;

    public AbstractAircraftModel(int id, String manufacturer, String modelName, AircraftPerformance aircraftPerformance,
                                 AircraftSize aircraftSize, AircraftWeight aircraftWeight, F aircraftModelFeature)
            throws IllegalArgumentException {
        this(manufacturer, modelName,aircraftPerformance, aircraftSize, aircraftWeight, aircraftModelFeature);
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
    }

    public AbstractAircraftModel(String manufacturer, String modelName, AircraftPerformance aircraftPerformance,
                                 AircraftSize aircraftSize, AircraftWeight aircraftWeight, F aircraftModelFeature)
            throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        ValidationUtil.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        ValidationUtil.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        ValidationUtil.validateArgument(manufacturer, Objects::nonNull, NULL_MANUFACTURER_MESSAGE);
        ValidationUtil.validateArgument(modelName, Objects::nonNull, NULL_MODEL_NAME_MESSAGE);
        ValidationUtil.validateArgument(aircraftModelFeature, Objects::nonNull, NULL_MODEL_FEATURE_MESSAGE);
        this.aircraftPerformance = aircraftPerformance;
        this.aircraftSize = aircraftSize;
        this.aircraftWeight = aircraftWeight;
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.aircraftModelFeature = aircraftModelFeature;
    }

    public AbstractAircraftModel(int id, Supplier<F> featureSupplier) throws IllegalArgumentException {
        this(featureSupplier);
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
    }

    public AbstractAircraftModel(Supplier<F> featureSupplier) throws IllegalArgumentException {
        ValidationUtil.validateArgument(featureSupplier, Objects::nonNull, NULL_MODEL_FEATURE_SUPPLIER_MESSAGE);
        aircraftPerformance = new AircraftPerformance();
        aircraftSize = new AircraftSize();
        aircraftWeight = new AircraftWeight();
        manufacturer = "";
        modelName = "";
        aircraftModelFeature = featureSupplier.get();
    }

    public AircraftPerformance getAircraftPerformance() {
        return aircraftPerformance;
    }

    public void setAircraftPerformance(AircraftPerformance aircraftPerformance) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        this.aircraftPerformance = aircraftPerformance;
    }

    public AircraftSize getAircraftSize() {
        return aircraftSize;
    }

    public void setAircraftSize(AircraftSize aircraftSize) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        this.aircraftSize = aircraftSize;
    }

    public AircraftWeight getAircraftWeight() {
        return aircraftWeight;
    }

    public int getId() {
        return id;
    }

    public void setAircraftWeight(AircraftWeight aircraftWeight) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        this.aircraftWeight = aircraftWeight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) throws IllegalArgumentException {
        ValidationUtil.validateArgument(manufacturer, Objects::nonNull, NULL_MANUFACTURER_MESSAGE);
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) throws IllegalArgumentException {
        ValidationUtil.validateArgument(modelName, Objects::nonNull, NULL_MODEL_NAME_MESSAGE);
        this.modelName = modelName;
    }

    public F getAircraftModelFeature() {
        return aircraftModelFeature;
    }

    public void setAircraftModelFeature(F aircraftModelFeature) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftModelFeature, Objects::nonNull, NULL_MODEL_FEATURE_MESSAGE);
        this.aircraftModelFeature = aircraftModelFeature;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(id, aircraftPerformance, aircraftSize, aircraftWeight, manufacturer, modelName,
                                 aircraftModelFeature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractAircraftModel<?> that = (AbstractAircraftModel<?>) o;
        return id == that.id && aircraftModelFeature.equals(that.aircraftModelFeature) &&
               aircraftPerformance.equals(that.aircraftPerformance) && aircraftSize.equals(that.aircraftSize)
               && aircraftWeight.equals(that.aircraftWeight) && manufacturer.equals(that.manufacturer)
               && modelName.equals(that.modelName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + id +
               ", manufacturer='" + getManufacturer() + '\'' +
               ", modelName='" + modelName + '\'' +
               ", aircraftPerformance=" + aircraftPerformance +
               ", aircraftSize=" + aircraftSize +
               ", aircraftWeight=" + aircraftWeight +
               ", aircraftModelFeature=" + aircraftModelFeature +
               '}';
    }
}
