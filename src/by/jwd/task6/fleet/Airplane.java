package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

import java.util.Objects;

public class Airplane implements RegisteredAircraft {

    private AirplaneModel model;
    private String registrationCode;
    private String notes;

    public Airplane(AirplaneModel model, String registrationCode) {
        this.model = model;
        this.registrationCode = registrationCode;
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
