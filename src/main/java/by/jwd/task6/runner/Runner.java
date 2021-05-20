package by.jwd.task6.runner;

import by.jwd.task6.airline.Airline;
import by.jwd.task6.airline.AirlineException;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.util.ArgumentValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    private Runner() {
    }

    public static void main(final String[] args) {
        try {
            List<Aircraft> aircrafts;
            var airline = Airline.getInstance();
            var fuelConsumptionPredicate = airline.createFilter(airplane -> airplane.getAircraftPerformance()
                                                                                    .getFuelConsumption(), 3000, 6000);

            aircrafts =  airline.filterFleet(fuelConsumptionPredicate);
            airline.sortFleet(aircrafts, List.of(Airline.MAX_RANGE_COMPARATOR, Airline.REGISTRATION_CODE_COMPARATOR));
            for (Aircraft aircraft : aircrafts) {
                LOGGER.info("{} {} ({}): {} m.",
                            aircraft.getAircraftDocument().getManufacturer(),
                            aircraft.getAircraftDocument().getModelName(),
                            aircraft.getAircraftDocument().getRegistrationCode(),
                            aircraft.getAircraftPerformance().getMaxRange());
            }
            double totalPassengerCapacity = airline.calculateTotal(Airline.AIRCRAFT_PASSENGER_CAPACITY_FUNCTION);
            double totalLoadCapacity = airline.calculateTotal(Airline.AIRCRAFT_LOAD_CAPACITY_FUNCTION);
            LOGGER.info("Total passenger capacity: {}.", String.format("%.0f", totalPassengerCapacity));
            LOGGER.info("Total load capacity: {}.", String.format("%.3f", totalLoadCapacity));
        } catch (AirlineException | ArgumentValidationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
