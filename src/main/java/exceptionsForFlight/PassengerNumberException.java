package exceptionsForFlight;

public class PassengerNumberException extends Exception{
    PassengerNumberException(String errorMessage){
        super(errorMessage);
    }
}
