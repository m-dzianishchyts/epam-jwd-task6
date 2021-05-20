package by.jwd.task6.runner;

import by.jwd.task6.airline.Airline;
import by.jwd.task6.airline.AirlineException;
import by.jwd.task6.fleet.Aircraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public final class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    private Runner() {
    }

    public static void main(final String[] args) {
        try {
            var airline = Airline.getInstance();
            List<Aircraft> aircrafts = airline.collectAircrafts();
            var fuelConsumptionPredicate = airline.createFilter(airplane -> airplane.getAircraftPerformance()
                                                                                    .getFuelConsumption(), 3000, 6000);
            var maxRangeComparator = Airline.MAX_RANGE_COMPARATOR;
            var registrationCodeComparator = Airline.REGISTRATION_CODE_COMPARATOR;
            var filteredAirplanes = aircrafts.stream()
                                             .filter(fuelConsumptionPredicate)
                                             .sorted(maxRangeComparator.thenComparing(registrationCodeComparator))
                                             .collect(Collectors.toList());
            for (Aircraft aircraft : filteredAirplanes) {
                LOGGER.info("{} {} ({}): {} m.",
                            aircraft.getAircraftDocument().getManufacturer(),
                            aircraft.getAircraftDocument().getModelName(),
                            aircraft.getAircraftDocument().getRegistrationCode(),
                            aircraft.getAircraftPerformance().getMaxRange());
            }
        } catch (AirlineException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
