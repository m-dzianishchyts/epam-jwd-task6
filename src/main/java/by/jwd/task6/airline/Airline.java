package by.jwd.task6.airline;

import by.jwd.task6.dao.DaoException;
import by.jwd.task6.dao.serialization.AircraftSerializationDao;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.fleet.AircraftDocument;
import by.jwd.task6.fleet.AircraftPerformance;
import by.jwd.task6.fleet.AircraftSize;
import by.jwd.task6.fleet.AircraftWeight;
import by.jwd.task6.fleet.Airplane;
import by.jwd.task6.fleet.CargoAircraft;
import by.jwd.task6.fleet.CargoCompartment;
import by.jwd.task6.fleet.PassengerAircraft;
import by.jwd.task6.fleet.PassengerCompartment;
import by.jwd.task6.util.ValidationUtil;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Airline {

    /**
     * Aircraft flight performance comparators.
     */
    public static final Comparator<Aircraft> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftPerformance, AircraftPerformance.CRUISING_SPEED_COMPARATOR);
    public static final Comparator<Aircraft> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftPerformance, AircraftPerformance.FUEL_CONSUMPTION_COMPARATOR);
    public static final Comparator<Aircraft> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftPerformance, AircraftPerformance.MAX_ATTITUDE_COMPARATOR);
    public static final Comparator<Aircraft> MAX_RANGE_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftPerformance, AircraftPerformance.MAX_RANGE_COMPARATOR);

    /**
     * Aircraft size comparators.
     */
    public static final Comparator<Aircraft> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftSize, AircraftSize.FUSELAGE_LENGTH_COMPARATOR);
    public static final Comparator<Aircraft> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftSize, AircraftSize.FUSELAGE_WIDTH_COMPARATOR);
    public static final Comparator<Aircraft> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftSize, AircraftSize.OVERALL_HEIGHT_COMPARATOR);
    public static final Comparator<Aircraft> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftSize, AircraftSize.OVERALL_LENGTH_COMPARATOR);
    public static final Comparator<Aircraft> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftSize, AircraftSize.OVERALL_WIDTH_COMPARATOR);

    /**
     * Aircraft weight comparators.
     */
    public static final Comparator<Aircraft> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftWeight, AircraftWeight.EMPTY_WEIGHT_COMPARATOR);
    public static final Comparator<Aircraft> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftWeight, AircraftWeight.FUEL_CAPACITY_COMPARATOR);
    public static final Comparator<Aircraft> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftWeight, AircraftWeight.LANDING_WEIGHT_COMPARATOR);
    public static final Comparator<Aircraft> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftWeight, AircraftWeight.TAKEOFF_WEIGHT_COMPARATOR);

    /**
     * Aircraft document comparators.
     */
    public static final Comparator<Aircraft> REGISTRATION_CODE_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.REGISTRATION_CODE_COMPARATOR);
    public static final Comparator<Aircraft> MODEL_NAME_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.MODEL_NAME_COMPARATOR);
    public static final Comparator<Aircraft> MANUFACTURER_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.MODEL_NAME_COMPARATOR);
    public static final Comparator<Aircraft> NOTES_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.NOTES_COMPARATOR);

    /**
     * Aircraft own comparators.
     */
    public static final Comparator<Aircraft> ID_COMPARATOR = Comparator.comparing(Aircraft::getId);

    /**
     * Airplane own comparators.
     */
    public static final Comparator<Airplane> LANDING_DISTANCE_COMPARATOR =
            Comparator.comparing(Airplane::getLandingDistance);
    public static final Comparator<Airplane> TAKEOFF_DISTANCE_COMPARATOR =
            Comparator.comparing(Airplane::getTakeoffDistance);

    /**
     * Cargo aircraft model comparators.
     */
    public static final Comparator<CargoAircraft> HATCH_HEIGHT_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.HATCH_HEIGHT_COMPARATOR);
    public static final Comparator<CargoAircraft> HATCH_WIDTH_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.HATCH_WIDTH_COMPARATOR);
    public static final Comparator<CargoAircraft> TOTAL_AREA_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.TOTAL_AREA_COMPARATOR);
    public static final Comparator<CargoAircraft> TOTAL_HEIGHT_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.TOTAL_HEIGHT_COMPARATOR);
    public static final Comparator<CargoAircraft> TOTAL_LENGTH_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.TOTAL_LENGTH_COMPARATOR);
    public static final Comparator<CargoAircraft> TOTAL_VOLUME_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.TOTAL_VOLUME_COMPARATOR);
    public static final Comparator<CargoAircraft> TOTAL_WIDTH_COMPARATOR =
            Comparator.comparing(CargoAircraft::getCargoCompartment, CargoCompartment.TOTAL_WIDTH_COMPARATOR);

    /**
     * Passenger aircraft model comparators.
     */
    public static final Comparator<PassengerAircraft> PASSENGER_CAPACITY_COMPARATOR =
            Comparator.comparing(PassengerAircraft::getPassengerCompartment,
                                 PassengerCompartment.PASSENGER_CAPACITY_COMPARATOR);

    /**
     * Airline exception messages.
     */
    private static final String NULL_AIRCRAFT_VALUE_FUNCTION_MESSAGE = "Aircraft value function cannot be null.";

    private static final String SOURCE_FILE_PATH = "src/main/airplanes.ser";

    private static Airline instance;

    private final AircraftSerializationDao<Aircraft> aircraftDao;

    private Airline() throws AirlineException {
        try {
            aircraftDao = new AircraftSerializationDao<>(new File(SOURCE_FILE_PATH));
        } catch (DaoException e) {
            throw new AirlineException("An error occurred while initializing airline.");
        }
    }

    public static synchronized Airline getInstance() throws AirlineException {
        if (instance == null) {
            instance = new Airline();
        }
        return instance;
    }

    public Optional<Aircraft> findAircraftById(int id)
            throws AirlineException, IllegalArgumentException {
        try {
            Optional<Aircraft> result = aircraftDao.find(id);
            return result;
        } catch (DaoException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public List<Aircraft> collectAircrafts() throws AirlineException {
        try {
            List<Aircraft> aircrafts = aircraftDao.findAll();
            return aircrafts;
        } catch (DaoException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void writeOffAircraft(Aircraft aircraft)
            throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.remove(aircraft);
        } catch (DaoException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void acquireAircraft(Aircraft aircraft) throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.insert(aircraft);
        } catch (DaoException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void replaceAircraft(int id, Aircraft aircraft)
            throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.set(id, aircraft);
        } catch (DaoException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public Predicate<Aircraft> createFilter(Function<Aircraft, Number> aircraftValueFunction, double from, double to)
            throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftValueFunction, Objects::nonNull, NULL_AIRCRAFT_VALUE_FUNCTION_MESSAGE);
        return airplane -> {
            Number providedValue = aircraftValueFunction.apply(airplane);
            var valueAsDouble = providedValue.doubleValue();
            return valueAsDouble > from && valueAsDouble < to;
        };
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "aircraftDao=" + aircraftDao +
               '}';
    }
}
