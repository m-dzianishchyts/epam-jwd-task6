package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;

import java.util.Objects;

public class Airplane implements RegisteredAircraft {

    private AirplaneModel model;
    private String registrationCode;

    public Airplane(AirplaneModel model, String registrationCode) {
        this.model = model;
        this.registrationCode = registrationCode;
    }

    public Airplane() {
        model = new AirplaneModel();
        registrationCode = "";
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Airplane airplane = (Airplane) o;
        return registrationCode.equals(airplane.registrationCode) && model.equals(airplane.model);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(model, registrationCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "model=" + model +
               ", registrationCode='" + registrationCode + '\'' +
               '}';
    }
}
