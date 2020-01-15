package flightManagament;


import flight.Flight;
import flight.employees.*;
import flight.enums.Language;
import flight.enums.PilotStatus;
import flight.passengers.Passenger;

import java.util.*;

public class FlightManagement {
    public static void main(String[] args) {

        Flight flight1 = new Flight(1, Language.ENGLISH);

        creatAndAddToFlightDefaultCrew(flight1);
        creatDefaultPassengers(flight1, new Passenger(), 230);

        flight1.showEmployers();

        flight1.isFlightReady();
    }

    private static void creatAndAddToFlightDefaultCrew(Flight flight){
        Date birthDate = getDate(1980, 10,10);

        List<Language> languagesForFlightAttendant = new LinkedList<>();
        languagesForFlightAttendant.add(Language.POLISH);
        languagesForFlightAttendant.add(Language.ENGLISH);

        //Set 
        Pilot pilotOne = new Pilot(PilotStatus.PILOT_CAPTAIN, "ASDSADS", 698765567, birthDate, 11000);
        Pilot pilotTwo = new Pilot(PilotStatus.PILOT_NAVIGATOR, "JHDSUAIU", 725397261, birthDate, 10000);
        FlightAttendant flightAttendantOne = new FlightAttendant(languagesForFlightAttendant, "GISYDADHA", 546879098, birthDate, 8000);
        FlightAttendant flightAttendantTwo = new FlightAttendant(languagesForFlightAttendant, "MASDKAJS", 746354345, birthDate, 8000);
        FlightAttendant flightAttendantThree = new FlightAttendant(languagesForFlightAttendant, "KJDASUDSJ", 974638927, birthDate, 8000);

        flight.addPilot(pilotOne);
        flight.addPilot(pilotTwo);
        flight.addFlightAttendant(flightAttendantOne);
        flight.addFlightAttendant(flightAttendantTwo);
        flight.addFlightAttendant(flightAttendantThree);

//         ((Pilot)flight.getEmployees().get(0)).setHaveAnalogCompass(null);

        for (Employee employee : flight.getEmployees()){
            employee.setFlight(flight);
        }
    }

    private static void creatDefaultPassengers(Flight flight, Passenger passenger, int howManyPassenger){
        for (int i = 0; i < howManyPassenger; i++){
            flight.addPassenger(passenger);
        }
    }

    private static Date getDate(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
}
