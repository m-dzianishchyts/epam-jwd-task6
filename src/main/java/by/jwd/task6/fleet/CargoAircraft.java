package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;

public interface CargoAircraft {

    CargoCompartment getCargoCompartment();

    void setCargoCompartment(CargoCompartment cargoCompartment) throws ArgumentValidationException;
}
