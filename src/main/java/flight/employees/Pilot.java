package flight.employees;

import exceptionsForFlight.PilotAnalogCompassException;
import flight.enums.PilotStatus;
import flight.items.AnalogCompass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Pilot extends Employee {
    private PilotStatus status;
    private AnalogCompass analogCompass;

    /**
     *
     * @param status Pilot status which kind of pilot is it(e.g. CAPTAIN or NAVIGATOR)
     * @param name Pilot`s name
     * @param phoneNumber Pilot`s phone number
     * @param birthDate Pilot`s birth date
     * @param salary Pilot`s salary
     */
    public Pilot(PilotStatus status,
                 String name,
                 int phoneNumber,
                 Date birthDate,
                 int salary){

        super(name, phoneNumber, birthDate, salary);
        this.status = status;
        this.analogCompass = new AnalogCompass();
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
