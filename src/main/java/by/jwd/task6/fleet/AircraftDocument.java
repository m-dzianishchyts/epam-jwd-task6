package by.jwd.task6.fleet;

import by.jwd.task6.util.ArgumentValidationException;
import by.jwd.task6.util.HashUtil;

import java.io.Serializable;
import java.util.Comparator;

public class AircraftDocument implements Serializable {

    /**
     * Aircraft document properties comparator.
     */
    public static final Comparator<AircraftDocument> MANUFACTURER_COMPARATOR =
            Comparator.comparing(AircraftDocument::getManufacturer);
    public static final Comparator<AircraftDocument> MODEL_NAME_COMPARATOR =
            Comparator.comparing(AircraftDocument::getModelName);
    public static final Comparator<AircraftDocument> REGISTRATION_CODE_COMPARATOR =
            Comparator.comparing(AircraftDocument::getRegistrationCode);
    public static final Comparator<AircraftDocument> NOTES_COMPARATOR =
            Comparator.comparing(AircraftDocument::getNotes);

    /**
     * Aircraft document exception messages.
     */
    protected static final String NULL_MANUFACTURER_MESSAGE = "Manufacturer cannot be null";
    protected static final String NULL_MODEL_NAME_MESSAGE = "Model name cannot be null";
    protected static final String NULL_NOTES_MESSAGE = "Notes cannot be null";
    protected static final String NULL_REGISTRATION_CODE_MESSAGE = "Registration code cannot be null";

    private static final long serialVersionUID = -4460241147909729379L;

    private String manufacturer;
    private String modelName;
    private String notes;
    private String registrationCode;

    public AircraftDocument(String manufacturer, String modelName, String registrationCode, String notes)
            throws ArgumentValidationException {
        if (manufacturer == null) {
            throw new ArgumentValidationException(NULL_MANUFACTURER_MESSAGE);
        }
        if (modelName == null) {
            throw new ArgumentValidationException(NULL_MODEL_NAME_MESSAGE);
        }
        if (registrationCode == null) {
            throw new ArgumentValidationException(NULL_REGISTRATION_CODE_MESSAGE);
        }
        if (notes == null) {
            throw new ArgumentValidationException(NULL_NOTES_MESSAGE);
        }
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.registrationCode = registrationCode;
        this.notes = notes;
    }

    public AircraftDocument() {
        manufacturer = "";
        modelName = "";
        registrationCode = "";
        notes = "";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) throws ArgumentValidationException {
        if (manufacturer == null) {
            throw new ArgumentValidationException(NULL_MANUFACTURER_MESSAGE);
        }
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) throws ArgumentValidationException {
        if (modelName == null) {
            throw new ArgumentValidationException(NULL_MODEL_NAME_MESSAGE);
        }
        this.modelName = modelName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws ArgumentValidationException {
        if (notes == null) {
            throw new ArgumentValidationException(NULL_NOTES_MESSAGE);
        }
        this.notes = notes;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) throws ArgumentValidationException {
        if (registrationCode == null) {
            throw new ArgumentValidationException(NULL_REGISTRATION_CODE_MESSAGE);
        }
        this.registrationCode = registrationCode;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(manufacturer, modelName, notes, registrationCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AircraftDocument that = (AircraftDocument) o;
        return manufacturer.equals(that.manufacturer) && modelName.equals(that.modelName)
               && notes.equals(that.notes) && registrationCode.equals(that.registrationCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "registrationCode='" + registrationCode + '\'' +
               ", manufacturer='" + manufacturer + '\'' +
               ", modelName='" + modelName + '\'' +
               ", notes='" + notes + '\'' +
               '}';
    }
}
