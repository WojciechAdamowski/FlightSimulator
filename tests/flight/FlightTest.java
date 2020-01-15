package flight;

import flight.employees.Employee;
import flight.employees.FlightAttendant;
import flight.employees.Pilot;
import flight.enums.Language;
import flight.enums.PilotStatus;
import flight.passengers.Passenger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight TEST_FLIGHT;
    private Pilot TEST_PILOT;
    private FlightAttendant TEST_FLIGHT_ATTENDANT;
    private Passenger TEST_PASSENGER;
    private static Date birthDate;
    private static List<Language> languagesForFlightAttendant = new LinkedList<>();
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeAll
    static void createLanguagesForFlightAttendantAndBirthDate(){
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        birthDate =  calendar.getTime();
    }

    @BeforeEach
    void createDefaultFlight(){
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_PILOT = new Pilot(PilotStatus.PILOT_CAPTAIN, "Janusz", 764435967, birthDate, 11000);
        TEST_FLIGHT_ATTENDANT = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, birthDate, 5000);
        TEST_PASSENGER = new Passenger();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testIsFlightReadyMethodWithCorrectVariables(){
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        for (Employee employee : TEST_FLIGHT.getEmployees()){
            employee.setFlight(TEST_FLIGHT);
        }
        for (int i = 0; i < 240; i++){
            TEST_FLIGHT.addPassenger(TEST_PASSENGER);
        }

        assertTrue(TEST_FLIGHT.isFlightReady(), "Flight should be ready, but is not");
    }

    @Test
    void addPilotCheckOfNullPilot(){
        TEST_FLIGHT.addPilot(null);
        assertEquals("Given pilot doesn't exist, there is NULL !\n" , outContent.toString());
    }

    @Test
    void addFlightAttendantCheckOfNullFlightAttendant(){
        TEST_FLIGHT.addFlightAttendant(null);
        assertEquals("Given Flight attendant doesn't exist, there is NULL !\n", outContent.toString());
    }

    @Test
    void addPassengerCheckOfNullPassenger(){
        TEST_FLIGHT.addPassenger(null);
        assertEquals("Given Passenger doesn't exist, there is NULL !\n", outContent.toString());
    }

    @Test
    void isFlightReadyWhenWeHaveNotReadyPilot() {
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        for (Employee employee : TEST_FLIGHT.getEmployees()){
            employee.setFlight(TEST_FLIGHT);
        }
        for (int i = 0; i < 240; i++){
            TEST_FLIGHT.addPassenger(TEST_PASSENGER);
        }

        Employee firstPilot = TEST_FLIGHT.getEmployees().get(0);
        ((Pilot) firstPilot).setHaveAnalogCompass(null);
        TEST_FLIGHT.isFlightReady();
        assertEquals("exceptionsForFlight.PilotAnalogCompassException: Pilot is not ready because there is no compass\n", outContent.toString());
    }

    @Test
    void isFlightReadyWhenWeHaveNotReadyFlightAttendant() {
        TEST_FLIGHT = new Flight(1, Language.FRENCH);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        for (Employee employee : TEST_FLIGHT.getEmployees()){
            employee.setFlight(TEST_FLIGHT);
        }
        for (int i = 0; i < 240; i++){
            TEST_FLIGHT.addPassenger(TEST_PASSENGER);
        }

        TEST_FLIGHT.isFlightReady();
        assertEquals("exceptionsForFlight.FlightAttendantLanguageException: Flight attendant don't know the right language!\n", outContent.toString());
    }

    @Test
    void isFlightReadyWhenWeDontHaveEnoughFlightAttendants(){
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        for (Employee employee : TEST_FLIGHT.getEmployees()){
            employee.setFlight(TEST_FLIGHT);
        }
        for (int i = 0; i < 240; i++){
            TEST_FLIGHT.addPassenger(TEST_PASSENGER);
        }

        TEST_FLIGHT.isFlightReady();
        assertEquals("Not enough flight attendant in flight\n", outContent.toString());
    }

    @Test
    void isFlightReadyWhenWeDontHaveEnoughPilots(){
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_FLIGHT.addPilot(TEST_PILOT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        TEST_FLIGHT.addFlightAttendant(TEST_FLIGHT_ATTENDANT);
        for (Employee employee : TEST_FLIGHT.getEmployees()){
            employee.setFlight(TEST_FLIGHT);
        }
        for (int i = 0; i < 240; i++){
            TEST_FLIGHT.addPassenger(TEST_PASSENGER);
        }
        TEST_FLIGHT.isFlightReady();
        assertEquals("Not enough pilots in flight\n", outContent.toString());
    }
}

