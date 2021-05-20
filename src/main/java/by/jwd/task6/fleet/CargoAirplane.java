package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.util.Objects;

public class CargoAirplane extends Airplane implements CargoAircraft {

    /**
     * Cargo airplane exception messages.
     */
    protected static final String NULL_CARGO_COMPARTMENT_MESSAGE = "Cargo compartment cannot be null.";

    private static final long serialVersionUID = 8652281243167429023L;

    private CargoCompartment cargoCompartment;

    public CargoAirplane(int id, AircraftPerformance aircraftPerformance, AircraftSize aircraftSize,
                         AircraftWeight aircraftWeight, AircraftDocument aircraftDocument,
                         CargoCompartment cargoCompartment, float takeoffDistance,
                         float landingDistance) throws IllegalArgumentException {
        super(id, aircraftPerformance, aircraftSize, aircraftWeight, aircraftDocument, takeoffDistance,
              landingDistance);
        ValidationUtil.validateArgument(cargoCompartment, Objects::nonNull, NULL_CARGO_COMPARTMENT_MESSAGE);
        this.cargoCompartment = cargoCompartment;
    }

    public CargoAirplane(int id) throws IllegalArgumentException {
        super(id);
    }

    public CargoAirplane() {
    }

    @Override
    public CargoCompartment getCargoCompartment() {
        return cargoCompartment;
    }

    @Override
    public void setCargoCompartment(CargoCompartment cargoCompartment) {
        ValidationUtil.validateArgument(cargoCompartment, Objects::nonNull, NULL_CARGO_COMPARTMENT_MESSAGE);
        this.cargoCompartment = cargoCompartment;
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
        CargoAirplane that = (CargoAirplane) o;
        return cargoCompartment.equals(that.cargoCompartment);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(super.hashCode(), cargoCompartment);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + getId() +
               ", " + AircraftDocument.class.getSimpleName() + "='" + getAircraftDocument() + '\'' +
               ", " + AircraftPerformance.class.getSimpleName() + "=" + getAircraftPerformance() +
               ", " + AircraftSize.class.getSimpleName() + "=" + getAircraftSize() +
               ", " + AircraftWeight.class.getSimpleName() + "=" + getAircraftWeight() +
               ", " + CargoCompartment.class.getSimpleName() + "=" + cargoCompartment +
               ", landingDistance=" + getLandingDistance() +
               ", takeoffDistance=" + getTakeoffDistance() +
               '}';
    }
}
