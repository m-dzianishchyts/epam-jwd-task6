package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.util.Comparator;
import java.util.function.Supplier;

public class AirplaneModel<F extends AircraftModelFeature> extends AbstractAircraftModel<F> {

    /**
     * Airplane model comparators.
     */
    public static final Comparator<AirplaneModel<?>> LANDING_DISTANCE_COMPARATOR =
            Comparator.comparing(AirplaneModel::getLandingDistance);
    public static final Comparator<AirplaneModel<?>> TAKEOFF_DISTANCE_COMPARATOR =
            Comparator.comparing(AirplaneModel::getTakeoffDistance);

    private static final long serialVersionUID = -4673073278962293223L;

    private float landingDistance;
    private float takeoffDistance;

    public AirplaneModel(String manufacturer, String modelName, AircraftPerformance aircraftPerformance,
                         AircraftSize aircraftSize, AircraftWeight aircraftWeight, F aircraftModelFeature,
                         float takeoffDistance, float landingDistance) throws IllegalArgumentException {
        this(0, manufacturer, modelName, aircraftPerformance, aircraftSize, aircraftWeight, aircraftModelFeature,
              takeoffDistance, landingDistance);
    }

    public AirplaneModel(int id, String manufacturer, String modelName, AircraftPerformance aircraftPerformance,
                         AircraftSize aircraftSize, AircraftWeight aircraftWeight, F aircraftModelFeature,
                         float takeoffDistance, float landingDistance) throws IllegalArgumentException {
        super(id, manufacturer, modelName, aircraftPerformance, aircraftSize, aircraftWeight, aircraftModelFeature);
        ValidationUtil.validateArgument(takeoffDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationUtil.validateArgument(landingDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.takeoffDistance = takeoffDistance;
        this.landingDistance = landingDistance;
    }

    public AirplaneModel(Supplier<F> featureSupplier) throws IllegalArgumentException {
        this(0, featureSupplier);
    }

    public AirplaneModel(int id, Supplier<F> featureSupplier) throws IllegalArgumentException {
        super(id, featureSupplier);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(super.hashCode(), takeoffDistance, landingDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AirplaneModel<?> that = (AirplaneModel<?>) o;
        return that.takeoffDistance == takeoffDistance && that.landingDistance == landingDistance;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + getId() +
               ", manufacturer='" + getManufacturer() + '\'' +
               ", modelName='" + getModelName() + '\'' +
               ", aircraftPerformance=" + getAircraftPerformance() +
               ", aircraftSize=" + getAircraftSize() +
               ", aircraftWeight=" + getAircraftWeight() +
               ", aircraftModelFeature=" + getAircraftModelFeature() +
               ", landingDistance=" + landingDistance +
               ", takeoffDistance=" + takeoffDistance +
               '}';
    }

    public float getTakeoffDistance() {
        return takeoffDistance;
    }

    public void setTakeoffDistance(float takeoffDistance) throws IllegalArgumentException {
        ValidationUtil.validateArgument(takeoffDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.takeoffDistance = takeoffDistance;
    }

    public float getLandingDistance() {
        return landingDistance;
    }

    public void setLandingDistance(float landingDistance) throws IllegalArgumentException {
        ValidationUtil.validateArgument(landingDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.landingDistance = landingDistance;
    }
}
