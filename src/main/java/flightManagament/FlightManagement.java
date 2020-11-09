package flightManagament;


import date.SimpleDate;
import employmentDepartment.EmploymentDepartment;
import flight.Flight;
import flight.employees.Employee;
import flight.employees.FlightAttendant;
import flight.employees.Pilot;
import flight.enums.Language;
import flight.enums.PilotStatus;
import flight.passengers.Passenger;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This is application for create and management flight
 *
 * @version 1.0
 * @author Wojciech Adamowski
 * @since 2019-10-10
 */
public class FlightManagement {
    public static void main(String[] args) {
//        EmploymentDepartment employmentDepartment = EmploymentDepartment.getInstance();
//
//        employmentDepartment.hireNewPilot();

        SimpleDate simpleDate = new SimpleDate("1924-31-24");
    }

    /**
     * This method create and set default crew to flight which it`s get.
     * It`s create two languages, two pilots, three flight attendants and
     * set them to given flight.
     *
     * @param flight In this parameter we must give our flight in
     *               which this method create and set default crew.
     */
    private static void createAndAddToFlightDefaultCrew(Flight flight){
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

        for (Employee employee : flight.getEmployees()){
            employee.setFlight(flight);
        }
    }

    /**
     * This method create given number of passengers and set them to given flight
     *
     * @param flight This is a flight in which we set passengers
     * @param passenger This is create earlier passenger
     * @param howManyPassenger This is number passenger to create and set
     */
    private static void createDefaultPassengers(Flight flight, Passenger passenger, int howManyPassenger){
        for (int i = 0; i < howManyPassenger; i++){
            flight.addPassenger(passenger);
        }
    }

    /**
     * This function return date in format (year,month,day)
     *
     * @param year Year which we want set
     * @param month Month which we want set
     * @param day Day which we want set
     * @return Formatted date
     */
    private static Date getDate(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
}
