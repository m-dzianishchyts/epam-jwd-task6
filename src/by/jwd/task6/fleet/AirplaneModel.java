package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

import java.io.Serializable;

public class AirplaneModel extends AbstractAircraftModel implements Serializable {

    private float landingDistance;
    private float takeoffDistance;

    public AirplaneModel(AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                         AircraftWeight aircraftWeight, String registrationCode, float takeoffDistance,
                         float landingDistance) throws IllegalArgumentException {
        super(aircraftPerformance, aircraftSize, aircraftWeight, registrationCode);
        ValidationHelper.validateArgument(takeoffDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationHelper.validateArgument(landingDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.takeoffDistance = takeoffDistance;
        this.landingDistance = landingDistance;
    }

    public AirplaneModel() {
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
        AirplaneModel that = (AirplaneModel) o;
        return that.takeoffDistance == takeoffDistance && that.landingDistance == landingDistance;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "modelName='" + modelName + '\'' +
               ", aircraftPerformance=" + aircraftPerformance +
               ", aircraftSize=" + aircraftSize +
               ", aircraftWeight=" + aircraftWeight +
               ", landingDistance=" + landingDistance +
               ", takeoffDistance=" + takeoffDistance +
               '}';
    }

    public float getTakeoffDistance() {
        return takeoffDistance;
    }

    public void setTakeoffDistance(float takeoffDistance) {
        ValidationHelper.validateArgument(takeoffDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.takeoffDistance = takeoffDistance;
    }

    public float getLandingDistance() {
        return landingDistance;
    }

    public void setLandingDistance(float landingDistance) {
        ValidationHelper.validateArgument(landingDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.landingDistance = landingDistance;
    }
}
