package flight;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import exceptionsForFlight.PilotAnalogCompassException;


public interface Verifiable {
    /**
     * This method checks that are employers ready to flight:           <br>
     *     - Flight attendants - check that they know flight language   <br>
     *     - Pilots - they have analog compasses                        <br>
     *     - All - have set flight
     *
     * @return Some kind of exceptions
     * @throws FlightIsNotSetException Flight is not set to employer
     * @throws FlightAttendantLanguageException Flight attendants dont know specific language
     * @throws PilotAnalogCompassException Pilots dont have analog compasses
     */
    boolean checkReady() throws
            FlightIsNotSetException,
            FlightAttendantLanguageException,
            PilotAnalogCompassException;
}
