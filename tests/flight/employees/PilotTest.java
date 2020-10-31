package flight.employees;
import exceptionsForFlight.PilotAnalogCompassException;
import flight.Flight;
import flight.enums.Language;
import flight.enums.PilotStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

class PilotTest {
    private static Pilot TEST_PILOT;
    private static Flight TEST_FLIGHT;

    @BeforeAll
    static void createDefaultPilot(){
        Calendar calendar = Calendar.getInstance();
        Date birthDate = calendar.getTime();
        TEST_PILOT = new Pilot(PilotStatus.PILOT_CAPTAIN, "Janusz", 764435967, birthDate, 11000);
        TEST_FLIGHT = new Flight(1, Language.ENGLISH);
    }

    @Test
    void checkReadyWhilePilotHaveAnalogCompass() throws PilotAnalogCompassException{
        assertTrue(TEST_PILOT.checkReady(), "Pilot should be ready for flight but he not");
    }

    @Test
    void checkReadyWhilePilotDoesNotHaveAnalogCompass() {
        TEST_PILOT.setAnalogCompass(null);
        assertThrows(PilotAnalogCompassException.class, TEST_PILOT::checkReady, "Pilot should not have analog compass and not be ready to flight but he is!");
    }
}