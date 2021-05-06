package by.jwd.task6.fleet;

public interface RegisteredAircraft {

    String NULL_MODEL_MESSAGE = "Model cannot be null";
    String NULL_REGISTRATION_CODE_MESSAGE = "Registration code cannot be null";
    String NULL_NOTES_MESSAGE = "Notes cannot be null";

    AbstractAircraftModel getModel();

    String getRegistrationCode();

    void setRegistrationCode(String registrationCode) throws IllegalArgumentException;

    String getNotes();

    void setNotes(String notes) throws IllegalArgumentException;
}
