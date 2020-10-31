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

    /**
     * This method create languages for flight and birthdate for employers
     */
    @BeforeAll
    static void createLanguagesForFlightAttendantAndBirthDate(){
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        birthDate =  calendar.getTime();
    }

    /**
     * This method create new Flight with english
     * language, pilot, flight attendant in it
     */
    @BeforeEach
    void createDefaultFlight(){
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
        TEST_PILOT = new Pilot(PilotStatus.PILOT_CAPTAIN, "Janusz", 764435967, birthDate, 11000);
        TEST_FLIGHT_ATTENDANT = new FlightAttendant(languagesForFlightAttendant, "Katarzyna", 645756234, birthDate, 5000);
        TEST_PASSENGER = new Passenger();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * This method checks that the flight is ready
     * for take-off if it has all the necessary resources
     */
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

    /**
     * This method checks output of addPilot when we give in it null
     * pilot, this method must show up us message about pilot doesn't exist
     */
    @Test
    void addPilotCheckOfNullPilot(){
        TEST_FLIGHT.addPilot(null);
        assertEquals("Given pilot doesn't exist, there is NULL !\n" , outContent.toString());
    }

    /**
     * This method checks addFlightAttendant method in this situation
     * if we put in it null flight attendant it should show up error message
     */
    @Test
    void addFlightAttendantCheckOfNullFlightAttendant(){
        TEST_FLIGHT.addFlightAttendant(null);
        assertEquals("Given Flight attendant doesn't exist, there is NULL !\n", outContent.toString());
    }

    /**
     * This method checks addFlightAttendant method in this situation
     *  if we put in it null flight attendant it should show up error message
     */
    @Test
    void addPassengerCheckOfNullPassenger(){
        TEST_FLIGHT.addPassenger(null);
        assertEquals("Given Passenger doesn't exist, there is NULL !\n", outContent.toString());
    }

    /**
     * This method checks that is flight not ready for fly if we don't have
     * ready pilot
     */
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
        ((Pilot) firstPilot).setAnalogCompass(null);
        TEST_FLIGHT.isFlightReady();
        assertEquals("exceptionsForFlight.PilotAnalogCompassException: Pilot is not ready because there is no compass\n", outContent.toString());
    }

    /**
     * This method checks that is flight not ready for fly if we don't have
     * ready flight attendant
     */
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

    /**
     * This method checks that is flight not ready for fly if we don't have
     * enough flight attendants
     */
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

    /**
     * This method checks that is flight not ready for fly if we don't have
     * enough pilots
     */
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

