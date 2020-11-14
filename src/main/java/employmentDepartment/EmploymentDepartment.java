package employmentDepartment;

import date.SimpleDate;
import employmentDepartment.exceptions.employeeSalary.EmployeeSalaryIsNotANumberException;
import employmentDepartment.exceptions.employeeBirthdate.EmployeeBirthdateIsNotRealException;
import employmentDepartment.exceptions.employeeName.EmployeeNameDoesNotHaveTwoPartsException;
import employmentDepartment.exceptions.employeeName.EmployeeNameDoesNotHaveUpperCaseCharactersException;
import employmentDepartment.exceptions.employeeName.EmployeeNameIsEmptyException;
import employmentDepartment.exceptions.employeeName.EmployeeNameIsNotStringException;
import employmentDepartment.exceptions.employeePhoneNumber.EmployeePhoneDoesNotHaveNineNumbersException;
import employmentDepartment.exceptions.employeePhoneNumber.EmployeePhoneIsNotANumberException;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * This class represent employment department in our airport
 */
public abstract class EmploymentDepartment {

    String printInputForEmployeeName(Scanner input){
        System.out.print("Please write employee name(e.g. Janusz Kowalski): ");
        return input.nextLine();
    }

    String printInputForEmployeePhoneNumber(Scanner input){
        System.out.print("Please write employee phone number(e.g. 526876542): ");
        return input.nextLine();
    }

    String printInputForEmployeeBirthDate(Scanner input){
        System.out.print("Please write employee's birthDate(e.g. 2000-10-28): ");
        return input.nextLine();
    }

    String printInputForEmployeeSalary(Scanner input){
        System.out.print("Please write employee's salary(e.g. 1200): ");
        return input.nextLine();
    }

    /**
     * This method checks: <br>
     *     1. If given name is empty                                                <br>
     *     2. If given name have two parts                                          <br>
     *     3. If given name is only letters                                         <br>
     *     4. If given name have upper characters in first letter in each pieces    <br>
     *
     * @param nameToCheck Given name which we want to check
     * @return Name if everything is correct
     * @throws EmployeeNameIsEmptyException When name is empty
     * @throws EmployeeNameDoesNotHaveTwoPartsException When name does not have two pieces
     * @throws EmployeeNameIsNotStringException When name is not a string
     * @throws EmployeeNameDoesNotHaveUpperCaseCharactersException When name does not have upper case as first char
     */
    String checkCorrectnessAndGetEmployeeName(String nameToCheck) throws
            EmployeeNameIsEmptyException,
            EmployeeNameDoesNotHaveTwoPartsException,
            EmployeeNameIsNotStringException,
            EmployeeNameDoesNotHaveUpperCaseCharactersException
    {
        if (nameToCheck.isEmpty()){
            throw new EmployeeNameIsEmptyException("Given name is empty!");
        } else if (checkIfNameIsNotString(nameToCheck)){
            throw new EmployeeNameIsNotStringException("Given name is not a string at all");
        } else if (checkIsGivenNameDoesNotHaveTwoPieces(nameToCheck)){
            throw new EmployeeNameDoesNotHaveTwoPartsException("Given name does not have two parts");
        }  else if (checkIfNamePiecesIsNotUpperCase(nameToCheck)){
            throw new EmployeeNameDoesNotHaveUpperCaseCharactersException("Given name doesn't have upper case at first characters in pieces of name");
        } else {
            return nameToCheck;
        }
    }

    private boolean checkIsGivenNameDoesNotHaveTwoPieces(String name){
        String[] splitName = name.split(" ");
        return splitName.length != 2;
    }

    private boolean checkIfNameIsNotString(String name){
        String[] splitName = name.split(" ");
        for (String namePiece : splitName){
            for (Character character : namePiece.toCharArray()){
                if (!Character.isLetter(character)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfNamePiecesIsNotUpperCase(String name){
        String[] splitName = name.split(" ");
        for(String namePiece : splitName){
            if (!Character.isUpperCase(namePiece.charAt(0))){
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks:                                      <br>
     *     1. If given phone is number                          <br>
     *     2. If given phone has more or less than 9 numbers    <br>
     *
     * @param phoneToCheck phone number to check
     * @return checked phone number
     * @throws EmployeePhoneIsNotANumberException throw this exception if phone is not a number
     * @throws EmployeePhoneDoesNotHaveNineNumbersException throw this exception if phone does not have nine numbers
     */
    String checkCorrectnessAndGetEmployeePhoneNumber(String phoneToCheck) throws
            EmployeePhoneIsNotANumberException,
            EmployeePhoneDoesNotHaveNineNumbersException
    {
        if (checkIfStringIsNotANumber(phoneToCheck)){
            throw new EmployeePhoneIsNotANumberException("Given phone is not a number!");
        } else if (checkIfPhoneDoesNotHaveNineNumbers(phoneToCheck)){
            throw new EmployeePhoneDoesNotHaveNineNumbersException("Given phone does not have nine numbers!");
        } else {
            return phoneToCheck;
        }
    }

    private boolean checkIfStringIsNotANumber(String string){
        for (Character character : string.toCharArray()){
            if (!Character.isDigit(character)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfPhoneDoesNotHaveNineNumbers(String phone){
        return !(phone.length() == 9);
    }

    /**
     * This method checks: <br>
     *     1. If given birthDate have three peaces separated by "-" <br>
     *     2. If given birthDate is probably real date
     *
     * @param birthDateToCheck given by user birthDate
     * @return checked birthDate
     */
    Date checkCorrectnessAndGetEmployeeBirthDate(String birthDateToCheck) throws
            EmployeeBirthdateIsNotRealException
    {
        if (checkIfDateIsNotProbablyReal(birthDateToCheck)){
            throw new EmployeeBirthdateIsNotRealException("Employee birth date must be real!");
        } else {
            SimpleDate simpleDate = new SimpleDate(birthDateToCheck);
            return simpleDate.getSimpleDateInDate();
        }

    }

    private boolean checkIfDateIsNotProbablyReal(String date){
        int years = Integer.parseInt(date.split("-")[0]);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - years > 80 || currentYear - years < -80;
    }

    /**
     * This method checks: <br>
     *     1. If given salary is NOT a number
     *
     * @param salaryToCheck String to check
     * @return Checked salary or throw an exception
     * @throws EmployeeSalaryIsNotANumberException Exception informs that employee's salary is NOT a number
     */
    String checkCorrectnessAndGetEmployeeSalary(String salaryToCheck) throws EmployeeSalaryIsNotANumberException {
        if (checkIfStringIsNotANumber(salaryToCheck)){
            throw new EmployeeSalaryIsNotANumberException("Employee salary must be a number!");
        } else {
            return salaryToCheck;
        }
    }

}
