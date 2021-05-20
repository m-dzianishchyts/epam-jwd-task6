package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

public class Airplane extends Aircraft {

    private static final long serialVersionUID = 2104449863946938187L;

    private float landingDistance;
    private float takeoffDistance;

    public Airplane(int id, AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                    AircraftWeight aircraftWeight, AircraftDocument aircraftDocument,
                    float takeoffDistance, float landingDistance) throws IllegalArgumentException {
        super(id, aircraftPerformance, aircraftSize, aircraftWeight, aircraftDocument);
        ValidationUtil.validateArgument(takeoffDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        ValidationUtil.validateArgument(landingDistance, FINITE_POSITIVE_PREDICATE, INVALID_FLIGHT_PROPERTY_MESSAGE);
        this.takeoffDistance = takeoffDistance;
        this.landingDistance = landingDistance;
    }

    public Airplane(int id) throws IllegalArgumentException {
        super(id);
    }

    public Airplane() {
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
        Airplane that = (Airplane) o;
        return that.takeoffDistance == takeoffDistance && that.landingDistance == landingDistance;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + getId() +
               ", " + AircraftDocument.class.getSimpleName() + "='" + getAircraftDocument() + '\'' +
               ", " + AircraftPerformance.class.getSimpleName() + "=" + getAircraftPerformance() +
               ", " + AircraftSize.class.getSimpleName() + "=" + getAircraftSize() +
               ", " + AircraftWeight.class.getSimpleName() + "=" + getAircraftWeight() +
               ", landingDistance=" + getLandingDistance() +
               ", takeoffDistance=" + getTakeoffDistance() +
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
