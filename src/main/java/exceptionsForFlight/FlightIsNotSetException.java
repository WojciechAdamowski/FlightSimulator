package exceptionsForFlight;

public class FlightIsNotSetException extends Exception {
    public FlightIsNotSetException(String errorMessage){
        super(errorMessage);
    }
}
