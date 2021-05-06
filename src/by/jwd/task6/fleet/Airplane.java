package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

import java.util.Comparator;
import java.util.Objects;

public class Airplane implements RegisteredAircraft {

    /**
     * Aircraft flight performance comparators.
     */
    public final static Comparator<Airplane> CRUISING_SPEED_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftPerformance(),
                                 AircraftPerformance.CRUISING_SPEED_COMPARATOR);
    public final static Comparator<Airplane> FUEL_CONSUMPTION_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftPerformance(),
                                 AircraftPerformance.FUEL_CONSUMPTION_COMPARATOR);
    public final static Comparator<Airplane> MAX_ATTITUDE_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftPerformance(),
                                 AircraftPerformance.MAX_ATTITUDE_COMPARATOR);
    public final static Comparator<Airplane> MAX_RANGE_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftPerformance(),
                                 AircraftPerformance.RANGE_COMPARATOR);

    /**
     * Aircraft size comparators.
     */
    public final static Comparator<Airplane> FUSELAGE_LENGTH_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftSize(),
                                 AircraftSize.FUSELAGE_LENGTH_COMPARATOR);
    public final static Comparator<Airplane> FUSELAGE_WIDTH_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftSize(),
                                 AircraftSize.FUSELAGE_WIDTH_COMPARATOR);
    public final static Comparator<Airplane> OVERALL_HEIGHT_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_HEIGHT_COMPARATOR);
    public final static Comparator<Airplane> OVERALL_LENGTH_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_LENGTH_COMPARATOR);
    public final static Comparator<Airplane> OVERALL_WIDTH_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftSize(),
                                 AircraftSize.OVERALL_WIDTH_COMPARATOR);

    /**
     * Aircraft weight comparators.
     */
    public final static Comparator<Airplane> EMPTY_WEIGHT_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftWeight(),
                                 AircraftWeight.EMPTY_WEIGHT_COMPARATOR);
    public final static Comparator<Airplane> FUEL_CAPACITY_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftWeight(),
                                 AircraftWeight.FUEL_CAPACITY_COMPARATOR);
    public final static Comparator<Airplane> LANDING_WEIGHT_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftWeight(),
                                 AircraftWeight.LANDING_WEIGHT_COMPARATOR);
    public final static Comparator<Airplane> TAKEOFF_WEIGHT_COMPARATOR =
            Comparator.comparing(airplane -> airplane.getModel().getAircraftWeight(),
                                 AircraftWeight.TAKEOFF_WEIGHT_COMPARATOR);

    /**
     * Aircraft model name comparator.
     */
    public final static Comparator<Airplane> MODEL_NAME_COMPARATOR =
            Comparator.comparing(Airplane::getModel, AirplaneModel.MODEL_NAME_COMPARATOR);

    /**
     * Aircraft manufacturer comparator.
     */
    public final static Comparator<Airplane> MANUFACTURER_COMPARATOR =
            Comparator.comparing(Airplane::getModel, AirplaneModel.MANUFACTURER_COMPARATOR);

    /**
     * Airplane model landing distance comparator.
     */
    public static final Comparator<AirplaneModel> LANDING_DISTANCE_COMPARATOR =
            Comparator.comparing(AirplaneModel::getLandingDistance);

    /**
     * Airplane model takeoff distance comparator.
     */
    public static final Comparator<AirplaneModel> TAKEOFF_DISTANCE_COMPARATOR =
            Comparator.comparing(AirplaneModel::getTakeoffDistance);

    private AirplaneModel model;
    private String notes;
    private String registrationCode;

    public Airplane(AirplaneModel model, String registrationCode, String notes) {
        this.model = model;
        this.registrationCode = registrationCode;
        this.notes = notes;
    }

    public Airplane() {
        model = new AirplaneModel();
        registrationCode = "";
        notes = "";
    }

    public AirplaneModel getModel() {
        return model;
    }

    public void setModel(AirplaneModel model) {
        ValidationHelper.validateArgument(model, Objects::nonNull, RegisteredAircraft.NULL_MODEL_MESSAGE);
        this.model = model;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        ValidationHelper.validateArgument(registrationCode, Objects::nonNull,
                                          RegisteredAircraft.NULL_REGISTRATION_CODE_MESSAGE);
        this.registrationCode = registrationCode;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) throws IllegalArgumentException {
        ValidationHelper.validateArgument(notes, Objects::nonNull, NULL_NOTES_MESSAGE);
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Airplane that = (Airplane) o;
        return registrationCode.equals(that.registrationCode)
               && model.equals(that.model) && notes.equals(that.notes);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(model, registrationCode, notes);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "registrationCode='" + registrationCode + '\'' +
               ", model=" + model +
               ", notes='" + notes + '\'' +
               '}';
    }
}
