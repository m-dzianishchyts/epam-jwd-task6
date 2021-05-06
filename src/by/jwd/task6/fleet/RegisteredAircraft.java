package by.jwd.task6.fleet;

import java.util.Comparator;

public interface RegisteredAircraft {

    /**
     * Registered aircraft notes comparator.
     */
    Comparator<RegisteredAircraft> NOTES_COMPARATOR =
            Comparator.comparing(RegisteredAircraft::getNotes);

    /**
     * Registered aircraft registration code comparator.
     */
    Comparator<RegisteredAircraft> REGISTRATION_CODE_COMPARATOR =
            Comparator.comparing(RegisteredAircraft::getRegistrationCode);

    String NULL_MODEL_MESSAGE = "Model cannot be null";
    String NULL_REGISTRATION_CODE_MESSAGE = "Registration code cannot be null";
    String NULL_NOTES_MESSAGE = "Notes cannot be null";

    AbstractAircraftModel getModel();

    String getRegistrationCode();

    void setRegistrationCode(String registrationCode) throws IllegalArgumentException;

    String getNotes();

    void setNotes(String notes) throws IllegalArgumentException;
}
