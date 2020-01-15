package flight.employees;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import flight.Flight;
import flight.enums.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightAttendantTest {

    private static Date BIRTH_DATE;
    private static Flight TEST_FLIGHT;

    @BeforeAll
    static void createDefaultDateAndFlight(){
        Calendar calendar = Calendar.getInstance();
        BIRTH_DATE =  calendar.getTime();
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
    }

    @Test
    void checkReadyForFlightAttendantWhenSheKnowRightLanguage() throws FlightIsNotSetException, FlightAttendantLanguageException {
        List<Language> languagesForFlightAttendant = new LinkedList<>();
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.ENGLISH);

        FlightAttendant flightAttendant = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, BIRTH_DATE, 5000);

        flightAttendant.setFlight(TEST_FLIGHT);

        assertTrue(flightAttendant.checkReady(), "Flight Attendant should be ready to flight but she doesn't");
    }

    @Test
    void checkReadyForFlightAttendantWhenSheDoesntKnowRightLanguage(){
        List<Language> languagesForFlightAttendant = new LinkedList<>();
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.FRENCH);

        FlightAttendant flightAttendant = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, BIRTH_DATE, 5000);

        flightAttendant.setFlight(TEST_FLIGHT);

        assertThrows(FlightAttendantLanguageException.class, flightAttendant::checkReady, "Flight Attendant should not be ready to flight, but she is");
    }

    @Test
    void checkReadyForEmptyFlightVariable(){
        List<Language> languagesForFlightAttendant = new LinkedList<>();
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.FRENCH);

        FlightAttendant flightAttendant = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, BIRTH_DATE, 5000);

        assertThrows(FlightIsNotSetException.class, flightAttendant::checkReady, "Flight attendant should not have a flight assigned, but she has");
    }

    @Test
    void checkReadyForFlightAttendantWhenSheDontKnowAnyLanguageNullList(){
        FlightAttendant flightAttendant = new FlightAttendant(null, "Katarzyna", 645756234, BIRTH_DATE, 5000);

        flightAttendant.setFlight(TEST_FLIGHT);

        assertThrows(FlightAttendantLanguageException.class, flightAttendant::checkReady, "Flight Attendant should not know any languages, but she know");
    }

    @Test
    void checkReadyForFlightAttendantWhenSheDontKnowAnyLanguageEmptyList(){
        List<Language> languagesForFlightAttendant = new LinkedList<>();

        FlightAttendant flightAttendant = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, BIRTH_DATE, 5000);

        flightAttendant.setFlight(TEST_FLIGHT);

        assertThrows(FlightAttendantLanguageException.class, flightAttendant::checkReady, "Flight Attendant should not be ready to flight, but she is");
    }
}