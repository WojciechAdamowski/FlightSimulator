package flight;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import exceptionsForFlight.PilotAnalogCompassException;

/**
 * For flight attendants, they check if they know
 * the appropriate language but for pilots it's check
 * if they have analog compass
 */
public interface Verifiable {
    boolean checkReady() throws
            FlightIsNotSetException,
            FlightAttendantLanguageException,
            PilotAnalogCompassException;
}
