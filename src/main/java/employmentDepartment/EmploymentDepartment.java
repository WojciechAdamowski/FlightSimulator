package employmentDepartment;

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
        PilotStatus pilotStatus = askAboutPilotStatus();

        System.out.println(pilotStatus);
    }

    private PilotStatus askAboutPilotStatus(){
        Scanner input = new Scanner(System.in);
        System.out.print("Choose one of two pilot status " +
                "\n 1. Captain " +
                "\n 2. Navigator " +
                "\nYou chose: ");

        String pilotStatus = input.nextLine();

        if (pilotStatus.equals("1")){
            return PilotStatus.PILOT_CAPTAIN;
        } else if (pilotStatus.equals("2")){
            return PilotStatus.PILOT_NAVIGATOR;
        } else {
            return null;
        }
    }
}
