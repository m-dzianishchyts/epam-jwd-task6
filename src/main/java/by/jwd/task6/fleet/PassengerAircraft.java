package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;

public interface PassengerAircraft {

    PassengerCompartment getPassengerCompartment();

    void setPassengerCompartment(PassengerCompartment passengerCompartment) throws ArgumentValidationException;
}
