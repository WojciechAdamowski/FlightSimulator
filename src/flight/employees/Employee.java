package flight.employees;

import flight.Verifiable;
import flight.Flight;

import java.util.Date;

public abstract class Employee implements Verifiable {
    protected Flight flight;
    private String name;
    private int phoneNumber;
    private Date birthDate;
    private int salary;

    Employee(String name, int phoneNumber, Date birthDate, int salary) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public void setFlight(Flight flight){  this.flight = flight; }

    public Flight getFlight() {
        return flight;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getSalary() {
        return salary;
    }
}
