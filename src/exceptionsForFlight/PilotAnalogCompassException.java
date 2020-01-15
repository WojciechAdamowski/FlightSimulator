package exceptionsForFlight;

public class PilotAnalogCompassException extends Exception {
    public PilotAnalogCompassException(String errorMessage){
        super(errorMessage);
    }
}
