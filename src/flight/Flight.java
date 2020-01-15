package flight;

import exceptionsForFlight.FlightAttendantLanguageException;
import exceptionsForFlight.FlightIsNotSetException;
import exceptionsForFlight.PassengerNumberException;
import exceptionsForFlight.PilotAnalogCompassException;
import flight.employees.*;
import flight.enums.Language;
import flight.passengers.Passenger;

import java.util.LinkedList;
import java.util.List;

public class Flight {
    private int id;
    private List<Employee> employees = new LinkedList<>();
    private List<Passenger> passengers = new LinkedList<>();
    private Language flightLanguage;

    public Flight(int id, Language flightLanguage){
        this.id = id;
        this.flightLanguage = flightLanguage;
    }

    public Language getFlightLanguage() {
        return flightLanguage;
    }

    public List<Employee> getEmployees() { return employees; }

    public int getId() { return id; }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPilot(Pilot pilot){
        if (pilot != null){
            employees.add(pilot);
        }else {
            System.out.println("Given pilot doesn't exist, there is NULL !");
        }
    }

    public void addFlightAttendant(FlightAttendant attendant) {
        if (attendant != null) {
            employees.add(attendant);
        }else {
            System.out.println("Given Flight attendant doesn't exist, there is NULL !");
        }
    }

    public void addPassenger(Passenger passenger){
        if (passenger != null) {
            passengers.add(passenger);
        }else {
            System.out.println("Given Passenger doesn't exist, there is NULL !");
        }
    }

    public void showEmployers(){
        System.out.println("##################################################");
        for (Employee employee : employees){
            System.out.println("Flight ID: " + this.id);
            System.out.println("Name: " + employee.getName());
            System.out.println("Phone Number: " + employee.getPhoneNumber());
            System.out.println("Birth Date: " + employee.getBirthDate());
            System.out.println("Salary: " + employee.getSalary());
            if (employee instanceof Pilot){
                System.out.println("Pilot status: " + ((Pilot) employee).getStatus().toString());
                System.out.println("Is Pilot have analog compass: " + ((Pilot) employee).getAnalogCompass());
            }else if (employee instanceof FlightAttendant){
                List<Language> learnLanguages = ((FlightAttendant) employee).getLanguages();
                System.out.print("Learn Languages: ");
                learnLanguages.forEach(Language -> System.out.print(Language.name() + " "));
                System.out.println();
            }
            System.out.println("##################################################");
        }
    }


    public boolean isFlightReady(){
        //check number of pilots
        int minimumNumberOfPilots = 2;
        boolean correctNumberOfPilots = true;
        if (countNumberOfPilotsInFlight() < minimumNumberOfPilots){
            System.out.println("Not enough pilots in flight");
            correctNumberOfPilots  = false;
        }

        //check number of flight attendant
        int minimumNumberOfFlightAttendant = 3;
        boolean correctNumberOfFlightAttendant = true;
        if (countNumberOfFlightAttendantInFlightStaff() < minimumNumberOfFlightAttendant){
            System.out.println("Not enough flight attendant in flight");
            correctNumberOfFlightAttendant = false;
        }

        //check employee
        boolean employeeReadyStatus = false;
        for (Employee employee : employees){
            try{
                employeeReadyStatus = employee.checkReady();
            }catch (FlightIsNotSetException | FlightAttendantLanguageException | PilotAnalogCompassException error){
                System.out.println(error.toString());
                employeeReadyStatus = false;
                break;
            }
        }

        //check number of passengers
        int minimumNumberOfPassengers = 240;
        int maximNumberOfPassengers = 200;
        boolean correctNumberOfPassengers = true;
        if (passengers.size() > minimumNumberOfPassengers || maximNumberOfPassengers > passengers.size()){
            System.out.println("Flight is NOT ready because of passengers");
            correctNumberOfPassengers = false;
        }

        boolean isFlightReady = (correctNumberOfPilots && correctNumberOfFlightAttendant && correctNumberOfPassengers && employeeReadyStatus);

        if (isFlightReady){
            System.out.println("FLIGHT IS READY !!");
        }

        return isFlightReady;
    }

    private int countNumberOfPilotsInFlight(){
        int numberOfPilotsInFlightStaff = 0;
        for (Employee employee : employees){
            if (employee instanceof Pilot){
                numberOfPilotsInFlightStaff++;
            }
        }
        return numberOfPilotsInFlightStaff;
    }

    private int countNumberOfFlightAttendantInFlightStaff(){
        int numberOfFlightAttendantsInFlightStaff = 0;
        for (Employee employee : employees){
            if (employee instanceof FlightAttendant){
                numberOfFlightAttendantsInFlightStaff++;
            }
        }
        return numberOfFlightAttendantsInFlightStaff;
    }
}
