package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class Aircraft implements Serializable {

    /**
     * Predicate for validating aircraft parameters.
     */
    protected static final Predicate<Float> FINITE_POSITIVE_PREDICATE = number -> number > 0 && Float.isFinite(number);

    /**
     * Aircraft own exception messages.
     */
    protected static final String INVALID_ID_MESSAGE = "Aircraft model ID cannot be negative.";
    protected static final String NULL_AIRCRAFT_DOCUMENT_MESSAGE = "Aircraft document cannot be null.";
    protected static final String NULL_AIRCRAFT_PERFORMANCE_MESSAGE = "Aircraft performance cannot be null.";
    protected static final String NULL_AIRCRAFT_SIZE_MESSAGE = "Aircraft size cannot be null.";
    protected static final String NULL_AIRCRAFT_WEIGHT_MESSAGE = "Aircraft weight cannot be null.";

    /**
     * Aircraft model properties exception messages.
     */
    protected static final String INVALID_SIZE_PROPERTY_MESSAGE = "Size property must be finite and positive.";
    protected static final String INVALID_WEIGHT_PROPERTY_MESSAGE = "Weight property must be finite and positive.";
    protected static final String INVALID_FLIGHT_PROPERTY_MESSAGE = "Flight property must be finite and positive.";

    private static final long serialVersionUID = 6716985004306569600L;

    private AircraftDocument aircraftDocument;
    private AircraftPerformance aircraftPerformance;
    private AircraftSize aircraftSize;
    private AircraftWeight aircraftWeight;
    private final int id;

    protected Aircraft(int id, AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                    AircraftWeight aircraftWeight, AircraftDocument aircraftDocument) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftPerformance, Objects::nonNull, NULL_AIRCRAFT_PERFORMANCE_MESSAGE);
        ValidationUtil.validateArgument(aircraftSize, Objects::nonNull, NULL_AIRCRAFT_SIZE_MESSAGE);
        ValidationUtil.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        ValidationUtil.validateArgument(aircraftDocument, Objects::nonNull, NULL_AIRCRAFT_DOCUMENT_MESSAGE);
        ValidationUtil.validateArgument(id, n -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
        this.aircraftDocument = aircraftDocument;
        this.aircraftPerformance = aircraftPerformance;
        this.aircraftSize = aircraftSize;
        this.aircraftWeight = aircraftWeight;
    }

    protected Aircraft(int id) throws IllegalArgumentException {
        ValidationUtil.validateArgument(id, n -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
        aircraftDocument = new AircraftDocument();
        aircraftPerformance = new AircraftPerformance();
        aircraftSize = new AircraftSize();
        aircraftWeight = new AircraftWeight();
    }

    protected Aircraft() {
        this(0);
    }

    public int getId() {
        return id;
    }

    public AircraftDocument getAircraftDocument() {
        return aircraftDocument;
    }

    public void setAircraftDocument(AircraftDocument aircraftDocument) {
        ValidationUtil.validateArgument(aircraftDocument, Objects::nonNull, NULL_AIRCRAFT_DOCUMENT_MESSAGE);
        this.aircraftDocument = aircraftDocument;
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

    public void setAircraftWeight(AircraftWeight aircraftWeight) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftWeight, Objects::nonNull, NULL_AIRCRAFT_WEIGHT_MESSAGE);
        this.aircraftWeight = aircraftWeight;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(id, aircraftDocument, aircraftPerformance, aircraftSize, aircraftWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aircraft that = (Aircraft) o;
        return id == that.id && aircraftDocument.equals(that.aircraftDocument)
               && aircraftPerformance.equals(that.aircraftPerformance) && aircraftSize.equals(that.aircraftSize)
               && aircraftWeight.equals(that.aircraftWeight);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + id +
               ", " + AircraftDocument.class.getSimpleName() + "='" + aircraftDocument + '\'' +
               ", " + AircraftPerformance.class.getSimpleName() + "=" + aircraftPerformance +
               ", " + AircraftSize.class.getSimpleName() + "=" + aircraftSize +
               ", " + AircraftWeight.class.getSimpleName() + "=" + aircraftWeight +
               '}';
    }
}
