package flight.employees;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import flight.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class FlightAttendant extends Employee {
    private List<Language> languages;

    /**
     *
     * @param languages List of languages which flight attendant know
     * @param name Flight attendant`s name
     * @param phoneNumber Flight attendant`s phone number
     * @param birthDate Flight attendant`s birthdate
     * @param salary Flight attendant`s salary
     */
    public FlightAttendant(List<Language> languages,
                           String name,
                           int phoneNumber,
                           Date birthDate,
                           int salary){

        super(name, phoneNumber, birthDate, salary);
        this.languages = languages;
    }

    /**
     * This method check that is flight attendant know language use in flight
     *
     * @return It can throws two exceptions or True if flight attendant is ready to flight
     * @throws FlightIsNotSetException This exception means that flight is not set to flight attendant
     * @throws FlightAttendantLanguageException This exception means that flight attendant dont know flight language
     */
    private boolean isFlightAttendantKnownFlightLanguage() throws FlightIsNotSetException, FlightAttendantLanguageException {
        if (flight != null) {
            Language flightLanguage = flight.getFlightLanguage();
            if (languages != null) {
                for (Language language : languages) {
                    if (language.equals(flightLanguage)) {
                        return true;
                    }
                }
            }else {
                throw new FlightAttendantLanguageException("Flight attendant dont know any language");
            }
            throw new FlightAttendantLanguageException("Flight attendant don't know the right language!");
        }else {
            throw new FlightIsNotSetException("Flight attendant does not have a flight assigned!");
        }
    }

    @Override
    public boolean checkReady() throws FlightIsNotSetException, FlightAttendantLanguageException {
        return isFlightAttendantKnownFlightLanguage();
    }
}
