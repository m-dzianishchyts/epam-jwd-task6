package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class AircraftDocument implements Serializable {

    /**
     * Aircraft document properties comparator.
     */
    public static final Comparator<AircraftDocument> NOTES_COMPARATOR =
            Comparator.comparing(AircraftDocument::getNotes);
    public static final Comparator<AircraftDocument> REGISTRATION_CODE_COMPARATOR =
            Comparator.comparing(AircraftDocument::getRegistrationCode);

    private String notes;
    private String registrationCode;

    public AircraftDocument(String registrationCode, String notes) throws IllegalArgumentException {
        ValidationUtil.validateArgument(registrationCode, Objects::nonNull, Aircraft.NULL_REGISTRATION_CODE_MESSAGE);
        ValidationUtil.validateArgument(notes, Objects::nonNull, Aircraft.NULL_NOTES_MESSAGE);
        this.registrationCode = registrationCode;
        this.notes = notes;
    }

    public AircraftDocument() {
        registrationCode = "";
        notes = "";
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws IllegalArgumentException {
        ValidationUtil.validateArgument(notes, Objects::nonNull, Aircraft.NULL_NOTES_MESSAGE);
        this.notes = notes;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) throws IllegalArgumentException {
        ValidationUtil.validateArgument(registrationCode, Objects::nonNull, Aircraft.NULL_REGISTRATION_CODE_MESSAGE);
        this.registrationCode = registrationCode;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(notes, registrationCode);
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
        return notes.equals(that.notes) && registrationCode.equals(that.registrationCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "registrationCode='" + registrationCode + '\'' +
               ", notes='" + notes + '\'' +
               '}';
    }
}
