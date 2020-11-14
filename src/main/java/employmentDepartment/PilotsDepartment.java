package employmentDepartment;

import employmentDepartment.exceptions.employeeSalary.EmployeeSalaryIsNotANumberException;
import employmentDepartment.exceptions.employeeBirthdate.EmployeeBirthdateIsNotRealException;
import employmentDepartment.exceptions.employeeName.EmployeeNameDoesNotHaveTwoPartsException;
import employmentDepartment.exceptions.employeeName.EmployeeNameDoesNotHaveUpperCaseCharactersException;
import employmentDepartment.exceptions.employeeName.EmployeeNameIsEmptyException;
import employmentDepartment.exceptions.employeeName.EmployeeNameIsNotStringException;
import employmentDepartment.exceptions.employeePhoneNumber.EmployeePhoneDoesNotHaveNineNumbersException;
import employmentDepartment.exceptions.employeePhoneNumber.EmployeePhoneIsNotANumberException;
import employmentDepartment.exceptions.employeeStatus.EmployeeStatusDoesNotExistException;
import flight.enums.PilotStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PilotsDepartment extends EmploymentDepartment{
    private static PilotsDepartment single_instance = null;

    public static PilotsDepartment getInstance()
    {
        if (single_instance == null)
            single_instance = new PilotsDepartment();

        return single_instance;
    }

    /**
     * This methods allows us to hire new pilot with
     * given attributes
     */
    public void hireNewPilot(){
        Scanner input = new Scanner(System.in);

        String pilotStatusNumber = this.printChooseMenuAndInputPilotStatus(input);
        String pilotName = super.printInputForEmployeeName(input);
        String pilotPhoneNumber = super.printInputForEmployeePhoneNumber(input);
        String pilotBirthDate = super.printInputForEmployeeBirthDate(input);
        String pilotSalary = super.printInputForEmployeeSalary(input);

        registerNewPilot(pilotStatusNumber, pilotName, pilotPhoneNumber, pilotBirthDate, pilotSalary);
    }

    private String printChooseMenuAndInputPilotStatus(Scanner input){
        System.out.print(
                "Choose one of two pilot status \n" +
                        " 1. Captain \n" +
                        " 2. Navigator \n" +
                        "You chose: "
        );
        return input.nextLine();
    }

    public void registerNewPilot(
            String pilotStatusToCheck,
            String pilotNameToCheck,
            String pilotPhoneNumberToCheck,
            String pilotBirthDateToCheck,
            String pilotSalaryToCheck
    ){
        try {
            PilotStatus pilotStatus = checkCorrectnessAndGetPilotStatus(pilotStatusToCheck);
            String pilotName = super.checkCorrectnessAndGetEmployeeName(pilotNameToCheck);
            String pilotPhoneNumber = super.checkCorrectnessAndGetEmployeePhoneNumber(pilotPhoneNumberToCheck);
            Date pilotBirthDate = super.checkCorrectnessAndGetEmployeeBirthDate(pilotBirthDateToCheck);
            String pilotSalary = super.checkCorrectnessAndGetEmployeeSalary(pilotSalaryToCheck);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(
                    "Status: " + pilotStatus + "\n" +
                            "Name: " + pilotName + "\n" +
                            "Phone Number: " + pilotPhoneNumber + "\n" +
                            "Birth date: " + format.format(pilotBirthDate) + "\n" +
                            "Salary: " + pilotSalary
            );
        }catch (EmployeeStatusDoesNotExistException |
                EmployeeNameIsEmptyException |
                EmployeeNameDoesNotHaveTwoPartsException |
                EmployeeNameIsNotStringException |
                EmployeeNameDoesNotHaveUpperCaseCharactersException |
                EmployeePhoneIsNotANumberException |
                EmployeePhoneDoesNotHaveNineNumbersException |
                EmployeeBirthdateIsNotRealException |
                EmployeeSalaryIsNotANumberException error){
            System.out.println(error.getMessage());
        }
    }

    /**
     * This method ask about which status of pilot we choose
     * <br>
     * We can choose from two statuses: CAPTAIN or NAVIGATOR
     * <br>
     * If we dont choose one of this two method will throws us
     * an exception about no such status for pilot
     * <br>
     * @param whichStatus This is number of status which we check
     *
     * @return This return is asked pilot status
     * @throws EmployeeStatusDoesNotExistException Where number does not match with exist status
     */
    private PilotStatus checkCorrectnessAndGetPilotStatus(String whichStatus) throws EmployeeStatusDoesNotExistException {
        if (whichStatus.equals("1")){
            return PilotStatus.PILOT_CAPTAIN;
        } else if (whichStatus.equals("2")){
            return PilotStatus.PILOT_NAVIGATOR;
        } else {
            throw new EmployeeStatusDoesNotExistException("There is no such status, please select the correct one!");
        }
    }
}
