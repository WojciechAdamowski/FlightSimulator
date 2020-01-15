package flight.employees;
import exceptionsForFlight.PilotAnalogCompassException;
import flight.enums.PilotStatus;
import flight.items.AnalogCompass;

import java.util.Date;

public class Pilot extends Employee {
    private PilotStatus status;
    private AnalogCompass analogCompass;

    public Pilot(PilotStatus status,
                 String name,
                 int phoneNumber,
                 Date birthDate,
                 int salary){

        super(name, phoneNumber, birthDate, salary);
        this.status = status;
        this.analogCompass = new AnalogCompass();
    }

    public void setHaveAnalogCompass(AnalogCompass haveAnalogCompass) {
        this.analogCompass = haveAnalogCompass;
    }

    public PilotStatus getStatus() {
        return status;
    }

    public AnalogCompass getAnalogCompass() {
        return analogCompass;
    }

    @Override
    public boolean checkReady() throws PilotAnalogCompassException {
        if (analogCompass != null){
            return true;
        }else {
            throw new PilotAnalogCompassException("Pilot is not ready because there is no compass");
        }
    }
}
