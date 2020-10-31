package flight.employees;

import flight.Verifiable;
import flight.Flight;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class Employee implements Verifiable {
    protected Flight flight;
    private String name;
    private int phoneNumber;
    private Date birthDate;
    private int salary;

    /**
     *
     * @param name Employer`s name
     * @param phoneNumber Employer`s phone number
     * @param birthDate Employer`s birth date
     * @param salary Employer`s salary
     */
    Employee(String name, int phoneNumber, Date birthDate, int salary) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.salary = salary;
    }
}
