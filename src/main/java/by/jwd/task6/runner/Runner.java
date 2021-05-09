package by.jwd.task6.runner;

import by.jwd.task6.airline.Airline;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.fleet.AirplaneModel;

import java.util.List;
import java.util.stream.Collectors;

public final class Runner {

    private Runner() {
    }

    public static void main(String[] args) throws Exception {
        Airline airline = Airline.getInstance();
        List<Aircraft<AirplaneModel<?>>> airplanes = airline.collectAirplanes();

        var fuelConsumptionPredicate = airline
                .createFiler(airplane -> airplane.getModel().getAircraftPerformance().getFuelConsumption(), 3000, 6000);
        var filteredAirplanes = airplanes.stream()
                                         .filter(fuelConsumptionPredicate)
                                         .sorted(Airline.RANGE_COMPARATOR
                                                         .thenComparing(Airline.REGISTRATION_CODE_COMPARATOR))
                                         .collect(Collectors.toList());
        for (Aircraft<AirplaneModel<?>> airplane : filteredAirplanes) {
            System.out.printf("%s %s (%s): %s m\n", airplane.getModel().getManufacturer(),
                              airplane.getModel().getModelName(), airplane.getAircraftDocument().getRegistrationCode(),
                              airplane.getModel().getAircraftPerformance().getRange());
        }
    }
}
