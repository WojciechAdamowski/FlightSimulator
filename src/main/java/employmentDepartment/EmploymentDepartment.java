package employmentDepartment;

import employmentDepartment.exceptions.*;
import flight.enums.PilotStatus;

import java.util.Scanner;

/**
 * This class represent employment department in our airport
 */
public class EmploymentDepartment {
    private static EmploymentDepartment single_instance = null;

    public static EmploymentDepartment getInstance()
    {
        if (single_instance == null)
            single_instance = new EmploymentDepartment();

        return single_instance;
    }

    /**
     * This methods allows us to hire new pilot with
     * given attributes
     */
    public void hireNewPilot(){
        Scanner input = new Scanner(System.in);
        String pilotStatusNumber = printChooseMenuAndInputPilotStatus(input);
        String pilotName = printInputForPilotName(input);
        registerNewPilot(pilotStatusNumber, pilotName);
    }


    public void registerNewPilot(String pilotStatusToCheck, String pilotNameToCheck){
        try {
            PilotStatus pilotStatus = checkCorrectnessAndGetPilotStatus(pilotStatusToCheck);
            String pilotName = checkCorrectnessAndGetPilotName(pilotNameToCheck);
            System.out.println("Status: " + pilotStatus + "\nName: " + pilotName);
        }catch (PilotStatusDoesNotExistException |
                PilotNameIsEmptyException |
                PilotNameDoesNotHaveTwoPartsException |
                PilotNameIsNotStringException |
                PilotNameDoesNotHaveUpperCaseCharactersException error){
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
     * @throws PilotStatusDoesNotExistException Where number does not match with exist status
     */
    private PilotStatus checkCorrectnessAndGetPilotStatus(String whichStatus) throws PilotStatusDoesNotExistException{
        if (whichStatus.equals("1")){
            return PilotStatus.PILOT_CAPTAIN;
        } else if (whichStatus.equals("2")){
            return PilotStatus.PILOT_NAVIGATOR;
        } else {
            throw new PilotStatusDoesNotExistException("There is no such status, please select the correct one!");
        }
    }

    /**
     * This method checks: <br>
     *     1. If given name is empty
     *     2. If given name have two parts
     *     3. If given name is only letters
     *     4. If given name have upper characters in first letter in each pieces
     *
     * @param nameToCheck Given name which we want to check
     * @return Name if everything is correct
     * @throws PilotNameIsEmptyException When name is empty
     * @throws PilotNameDoesNotHaveTwoPartsException When name does not have two pieces
     * @throws PilotNameIsNotStringException When name is not a string
     * @throws PilotNameDoesNotHaveUpperCaseCharactersException When name does not have upper case as first char
     */
    private String checkCorrectnessAndGetPilotName(String nameToCheck) throws
            PilotNameIsEmptyException,
            PilotNameDoesNotHaveTwoPartsException,
            PilotNameIsNotStringException,
            PilotNameDoesNotHaveUpperCaseCharactersException
    {
        if (nameToCheck.isEmpty()){
            throw new PilotNameIsEmptyException("Given name is empty!");
        } else if (checkIfNameIsNotString(nameToCheck)){
            throw new PilotNameIsNotStringException("Given name is not a string at all");
        } else if (checkIsGivenNameDoesNotHaveTwoPieces(nameToCheck)){
            throw new PilotNameDoesNotHaveTwoPartsException("Given name does not have two parts");
        }  else if (checkIfNamePiecesIsNotUpperCase(nameToCheck)){
            throw new PilotNameDoesNotHaveUpperCaseCharactersException("Given name doesn't have upper case at first characters in pieces of name");
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
     * This method print menu and get from user pilot status
     *
     * @return This is input from our user
     */
    private String printChooseMenuAndInputPilotStatus(Scanner input){
        System.out.print(
                "Choose one of two pilot status \n" +
                " 1. Captain \n" +
                " 2. Navigator \n" +
                "You chose: "
        );
        return input.nextLine();
    }

    /**
     * This method print text and input for user
     *
     * @param input This is scanner
     * @return This is input from our user
     */
    private String printInputForPilotName(Scanner input){
        System.out.print("Please write pilot name(e.g. Janusz Kowalski): ");
        return input.nextLine();
    }
}
