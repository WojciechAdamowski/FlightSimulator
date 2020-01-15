package flight.employees;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import flight.enums.Language;

import java.util.Date;
import java.util.List;

public class FlightAttendant extends Employee {
    private List<Language> languages;

    public FlightAttendant(List<Language> languages,
                           String name,
                           int phoneNumber,
                           Date birthDate,
                           int salary){

        super(name, phoneNumber, birthDate, salary);
        this.languages = languages;
    }

    public List<Language> getLanguages() {
        return languages;
    }

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
