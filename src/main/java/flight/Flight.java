package flight;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import exceptionsForFlight.PilotAnalogCompassException;
import flight.employees.Employee;
import flight.employees.FlightAttendant;
import flight.employees.Pilot;
import flight.enums.Language;
import flight.passengers.Passenger;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class Flight {
    private int id;
    private List<Employee> employees = new LinkedList<>();
    private List<Passenger> passengers = new LinkedList<>();
    private Language flightLanguage;

    /**
     *
     * @param id New id for flight (e.g. 328)
     * @param flightLanguage The language for flight which must be
     *                       taken from Enums (e.g. Language.ENGLISH)
     */
    public Flight(int id, Language flightLanguage) {
        this.id = id;
        this.flightLanguage = flightLanguage;
    }

    /**
     * This method allows add new pilot to flight
     *
     * @param pilot This parameter must get
     *              previously created pilot
     */
    public void addPilot(Pilot pilot) {
        if (pilot != null) {
            employees.add(pilot);
        } else {
            System.out.print("Given pilot doesn't exist, there is NULL !\n");
        }
    }

    /**
     * This method allows add new flight attendant to flight
     *
     * @param attendant This parameter must get previously
     *                  created flight attendant
     */
    public void addFlightAttendant(FlightAttendant attendant) {
        if (attendant != null) {
            employees.add(attendant);
        } else {
            System.out.print("Given Flight attendant doesn't exist, there is NULL !\n");
        }
    }

    /**
     * This method allows add new passenger to flight
     * @param passenger This parameter must get previously
     *                  created passenger
     */
    public void addPassenger(Passenger passenger) {
        if (passenger != null) {
            passengers.add(passenger);
        } else {
            System.out.print("Given Passenger doesn't exist, there is NULL !\n");
        }
    }

    /**
     * This method display all employers and them details:          <br>
     *  - All (Flight ID, Name, Phone Number, Birth Date, Salary)    <br>
     *  - For Pilots (Pilot status, Have analog compass)             <br>
     *  - For Flight Attendants (Languages)
     */
    public void showEmployers() {
        System.out.println("##################################################");
        for (Employee employee : employees) {
            System.out.println("Flight ID: " + this.id);
            System.out.println("Name: " + employee.getName());
            System.out.println("Phone Number: " + employee.getPhoneNumber());
            System.out.println("Birth Date: " + employee.getBirthDate());
            System.out.println("Salary: " + employee.getSalary());
            if (employee instanceof Pilot) {
                System.out.println("Pilot status: " + ((Pilot) employee).getStatus().toString());
                System.out.println("Is Pilot have analog compass: " + ((Pilot) employee).getAnalogCompass());
            } else if (employee instanceof FlightAttendant) {
                List<Language> learnLanguages = ((FlightAttendant) employee).getLanguages();
                System.out.print("Learn Languages: ");
                learnLanguages.forEach(Language -> System.out.print(Language.name() + " "));
                System.out.println();
            }
            System.out.println("##################################################");
        }
    }

    /**
     * This method check current flight is ready to start:      <br>
     *     1. Checks minimum number of pilots (2)               <br>
     *     2. Checks minimum number of Flight attendants (3)    <br>
     *     3. Checks is every employer is ready                 <br>
     *     4. Checks minimum number of passengers (240)         <br>
     *
     * After check display that flight is ready or not
     *
     * @return Return:                          <br>
     * - True if the flight is ready or         <br>
     * - False if flight is not ready
     */
    public boolean isFlightReady() {
        //check number of pilots
        int minimumNumberOfPilots = 2;
        boolean correctNumberOfPilots = true;
        if (countNumberOfPilotsInFlight() < minimumNumberOfPilots) {
            System.out.print("Not enough pilots in flight\n");
            correctNumberOfPilots = false;
        }

        //check number of flight attendant
        int minimumNumberOfFlightAttendant = 3;
        boolean correctNumberOfFlightAttendant = true;
        if (countNumberOfFlightAttendantInFlightStaff() < minimumNumberOfFlightAttendant) {
            System.out.print("Not enough flight attendant in flight\n");
            correctNumberOfFlightAttendant = false;
        }

        //check employee
        boolean employeeReadyStatus = false;
        for (Employee employee : employees) {
            try {
                employeeReadyStatus = employee.checkReady();
            } catch (FlightIsNotSetException | FlightAttendantLanguageException | PilotAnalogCompassException error) {
                System.out.print(error.toString() + "\n");
                employeeReadyStatus = false;
                break;
            }
        }

        //check number of passengers
        int minimumNumberOfPassengers = 240;
        int maximNumberOfPassengers = 200;
        boolean correctNumberOfPassengers = true;
        if (passengers.size() > minimumNumberOfPassengers || maximNumberOfPassengers > passengers.size()) {
            System.out.print("Flight is NOT ready because of passengers\n");
            correctNumberOfPassengers = false;
        }

        boolean isFlightReady = (correctNumberOfPilots && correctNumberOfFlightAttendant && correctNumberOfPassengers && employeeReadyStatus);

        if (isFlightReady) {
            System.out.println("FLIGHT IS READY !!");
        }

        return isFlightReady;
    }

    /**
     * This method counts number of pilots in flight
     *
     * @return Number of pilots in flight
     */
    private int countNumberOfPilotsInFlight() {
        int numberOfPilotsInFlightStaff = 0;
        for (Employee employee : employees) {
            if (employee instanceof Pilot) {
                numberOfPilotsInFlightStaff++;
            }
        }
        return numberOfPilotsInFlightStaff;
    }

    /**
     * This method count number of flight attendants in flight
     *
     * @return Number of flight attendants
     */
    private int countNumberOfFlightAttendantInFlightStaff() {
        int numberOfFlightAttendantsInFlightStaff = 0;
        for (Employee employee : employees) {
            if (employee instanceof FlightAttendant) {
                numberOfFlightAttendantsInFlightStaff++;
            }
        }
        return numberOfFlightAttendantsInFlightStaff;
    }
}
