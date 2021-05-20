package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;
import by.jwd.task6.util.HashUtil;

public class PassengerAirplane extends Airplane implements PassengerAircraft {

    /**
     * Passenger airplane exception messages.
     */
    protected static final String NULL_PASSENGER_COMPARTMENT_MESSAGE = "Passenger compartment cannot be null.";

    private static final long serialVersionUID = 3171551560107504889L;

    private PassengerCompartment passengerCompartment;

    public PassengerAirplane(int id, AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                             AircraftWeight aircraftWeight, AircraftDocument aircraftDocument,
                             PassengerCompartment passengerCompartment, float takeoffDistance,
                             float landingDistance) throws ArgumentValidationException {
        super(id, aircraftPerformance, aircraftSize, aircraftWeight, aircraftDocument, takeoffDistance,
              landingDistance);
        if (passengerCompartment == null) {
            throw new ArgumentValidationException(NULL_PASSENGER_COMPARTMENT_MESSAGE);
        }
        this.passengerCompartment = passengerCompartment;
    }

    public PassengerAirplane(int id) throws ArgumentValidationException {
        super(id);
    }

    public PassengerAirplane() {
    }

    @Override
    public PassengerCompartment getPassengerCompartment() {
        return passengerCompartment;
    }

    @Override
    public void setPassengerCompartment(PassengerCompartment passengerCompartment) throws ArgumentValidationException {
        if (passengerCompartment == null) {
            throw new ArgumentValidationException(NULL_PASSENGER_COMPARTMENT_MESSAGE);
        }
        this.passengerCompartment = passengerCompartment;
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
        PassengerAirplane that = (PassengerAirplane) o;
        return passengerCompartment.equals(that.passengerCompartment);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(super.hashCode(), passengerCompartment);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + getId() +
               ", " + AircraftDocument.class.getSimpleName() + "='" + getAircraftDocument() + '\'' +
               ", " + AircraftPerformance.class.getSimpleName() + "=" + getAircraftPerformance() +
               ", " + AircraftSize.class.getSimpleName() + "=" + getAircraftSize() +
               ", " + AircraftWeight.class.getSimpleName() + "=" + getAircraftWeight() +
               ", " + PassengerCompartment.class.getSimpleName() + "=" + passengerCompartment +
               ", landingDistance=" + getLandingDistance() +
               ", takeoffDistance=" + getTakeoffDistance() +
               '}';
    }
}
