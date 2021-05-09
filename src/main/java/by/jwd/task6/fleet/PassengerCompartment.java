package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;

public class PassengerCompartment implements AircraftModelFeature, Serializable {

    /**
     * Passenger compartment comparators.
     */
    public static final Comparator<PassengerCompartment> PASSENGER_CAPACITY_COMPARATOR =
            Comparator.comparing(PassengerCompartment::getPassengersCapacity);

    /**
     * Passenger compartment exception messages.
     */
    private static final String INVALID_PASSENGERS_CAPACITY_MESSAGE = "Passenger capacity must be positive.";

    private static final long serialVersionUID = -1010063071326469687L;

    private int passengersCapacity;

    public PassengerCompartment(int passengersCapacity) throws IllegalArgumentException {
        ValidationUtil.validateArgument(passengersCapacity, (n) -> n > 0, INVALID_PASSENGERS_CAPACITY_MESSAGE);
        this.passengersCapacity = passengersCapacity;
    }

    public PassengerCompartment() {
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) throws IllegalArgumentException {
        ValidationUtil.validateArgument(passengersCapacity, (n) -> n > 0, INVALID_PASSENGERS_CAPACITY_MESSAGE);
        this.passengersCapacity = passengersCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCompartment that = (PassengerCompartment) o;
        return passengersCapacity == that.passengersCapacity;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(passengersCapacity);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "passengersCapacity=" + passengersCapacity +
               '}';
    }
}
