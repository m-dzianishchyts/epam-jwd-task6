package by.jwd.task6.airline;

import by.jwd.task6.dao.DaoException;
import by.jwd.task6.dao.serialization.AircraftSerializationDao;
import by.jwd.task6.fleet.AbstractAircraftModel;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.fleet.AircraftDocument;
import by.jwd.task6.fleet.AircraftPerformance;
import by.jwd.task6.fleet.AircraftSize;
import by.jwd.task6.fleet.AircraftWeight;
import by.jwd.task6.fleet.AirplaneModel;
import by.jwd.task6.fleet.CargoCompartment;
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
     * Aircraft model flight performance comparators.
     */
    public static final Comparator<Aircraft<?>> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftPerformance(),
                                 AircraftPerformance.CRUISING_SPEED_COMPARATOR);
    public static final Comparator<Aircraft<?>> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftPerformance(),
                                 AircraftPerformance.FUEL_CONSUMPTION_COMPARATOR);
    public static final Comparator<Aircraft<?>> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftPerformance(),
                                 AircraftPerformance.MAX_ATTITUDE_COMPARATOR);
    public static final Comparator<Aircraft<?>> RANGE_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftPerformance(),
                                 AircraftPerformance.RANGE_COMPARATOR);

    /**
     * Aircraft model size comparators.
     */
    public static final Comparator<Aircraft<?>> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftSize(),
                                 AircraftSize.FUSELAGE_LENGTH_COMPARATOR);
    public static final Comparator<Aircraft<?>> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftSize(),
                                 AircraftSize.FUSELAGE_WIDTH_COMPARATOR);
    public static final Comparator<Aircraft<?>> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_HEIGHT_COMPARATOR);
    public static final Comparator<Aircraft<?>> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_LENGTH_COMPARATOR);
    public static final Comparator<Aircraft<?>> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_WIDTH_COMPARATOR);

    /**
     * Aircraft model weight comparators.
     */
    public static final Comparator<Aircraft<?>> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftWeight(),
                                 AircraftWeight.EMPTY_WEIGHT_COMPARATOR);
    public static final Comparator<Aircraft<?>> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftWeight(),
                                 AircraftWeight.FUEL_CAPACITY_COMPARATOR);
    public static final Comparator<Aircraft<?>> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftWeight(),
                                 AircraftWeight.LANDING_WEIGHT_COMPARATOR);
    public static final Comparator<Aircraft<?>> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(aircraft -> aircraft.getModel().getAircraftWeight(),
                                 AircraftWeight.TAKEOFF_WEIGHT_COMPARATOR);

    /**
     * Aircraft model own comparators.
     */
    public static final Comparator<Aircraft<?>> MODEL_NAME_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AirplaneModel.MODEL_NAME_COMPARATOR);
    public static final Comparator<Aircraft<?>> MANUFACTURER_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AirplaneModel.MANUFACTURER_COMPARATOR);
    public static final Comparator<Aircraft<?>> MODEL_ID_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.ID_COMPARATOR);

    /**
     * Aircraft document properties comparators.
     */
    public static final Comparator<Aircraft<?>> NOTES_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.NOTES_COMPARATOR);
    public static final Comparator<Aircraft<?>> REGISTRATION_CODE_COMPARATOR =
            Comparator.comparing(Aircraft::getAircraftDocument, AircraftDocument.REGISTRATION_CODE_COMPARATOR);

    /**
     * Airplane model comparators.
     */
    public static final Comparator<Aircraft<AirplaneModel<?>>> LANDING_DISTANCE_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AirplaneModel.LANDING_DISTANCE_COMPARATOR);
    public static final Comparator<Aircraft<AirplaneModel<?>>> TAKEOFF_DISTANCE_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AirplaneModel.TAKEOFF_DISTANCE_COMPARATOR);

    /**
     * Aircraft own comparators.
     */
    public static final Comparator<Aircraft<?>> ID_COMPARATOR = Comparator.comparing(Aircraft::getId);

    /**
     * Passenger aircraft model comparators.
     */
    public static final Comparator<Aircraft<AbstractAircraftModel<PassengerCompartment>>> PASSENGER_CAPACITY_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.PASSENGER_CAPACITY_COMPARATOR);

    /**
     * Cargo aircraft model comparators.
     */
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> HATCH_HEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.HATCH_HEIGHT_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> HATCH_WIDTH_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.HATCH_WIDTH_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> TOTAL_AREA_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.TOTAL_AREA_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> TOTAL_HEIGHT_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.TOTAL_HEIGHT_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> TOTAL_LENGTH_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.TOTAL_LENGTH_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> TOTAL_VOLUME_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.TOTAL_VOLUME_COMPARATOR);
    public static final Comparator<Aircraft<AbstractAircraftModel<CargoCompartment>>> TOTAL_WIDTH_COMPARATOR =
            Comparator.comparing(Aircraft::getModel, AbstractAircraftModel.TOTAL_WIDTH_COMPARATOR);

    /**
     * Airline exception messages.
     */
    private static final String NULL_EXTRACTOR_MESSAGE = "Extractor function cannot be null.";

    private static final String SOURCE_FILE_PATH = "src/main/airplanes.ser";

    private static Airline instance;

    private final AircraftSerializationDao<AirplaneModel<?>> aircraftDao;

    private Airline() throws AirlineException {
        try {
            aircraftDao = new AircraftSerializationDao<>(new File(SOURCE_FILE_PATH));
        } catch (DaoException e) {
            throw new AirlineException("An error occurred while initializing airline.");
        }
    }

    public static Airline getInstance() throws AirlineException {
        if (instance == null) {
            instance = new Airline();
        }
        return instance;
    }

    public Optional<Aircraft<AirplaneModel<?>>> findAirplaneById(int id)
            throws AirlineException, IllegalArgumentException {
        try {
            Optional<Aircraft<AirplaneModel<?>>> result = aircraftDao.find(id);
            return result;
        } catch (DaoException | ClassNotFoundException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public List<Aircraft<AirplaneModel<?>>> collectAirplanes() throws AirlineException {
        try {
            List<Aircraft<AirplaneModel<?>>> airplanes = aircraftDao.findAll();
            return airplanes;
        } catch (DaoException | ClassNotFoundException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void writeOffAirplane(Aircraft<AirplaneModel<?>> airplaneToWriteOff)
            throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.remove(airplaneToWriteOff);
        } catch (DaoException | ClassNotFoundException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void acquireAirplane(Aircraft<AirplaneModel<?>> airplaneToWriteOff)
            throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.insert(airplaneToWriteOff);
        } catch (DaoException | ClassNotFoundException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public void replaceAirplane(int id, Aircraft<AirplaneModel<?>> airplaneToWriteOff)
            throws AirlineException, IllegalArgumentException {
        try {
            aircraftDao.set(id, airplaneToWriteOff);
        } catch (DaoException | ClassNotFoundException e) {
            throw new AirlineException(e.getMessage(), e);
        }
    }

    public Predicate<Aircraft<AirplaneModel<?>>> createFiler(Function<Aircraft<AirplaneModel<?>>, Number> extractor,
                                                             double from, double to) throws IllegalArgumentException {
        ValidationUtil.validateArgument(extractor, Objects::nonNull, NULL_EXTRACTOR_MESSAGE);
        return (airplane) -> {
            Number providedValue = extractor.apply(airplane);
            double valueAsDouble = providedValue.doubleValue();
            return valueAsDouble > from && valueAsDouble < to;
        };
    }
}
