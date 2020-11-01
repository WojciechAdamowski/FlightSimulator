package employmentDepartment;

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
    public void hirePilotNewPilot(){
        Scanner input = new Scanner(System.in);
        System.out.print("Choose one of two pilot status " +
                "\n 1. Captain " +
                "\n 2. Navigator " +
                "\nYou chose: \n");

        String pilotStatus = input.nextLine();
        System.out.println(pilotStatus);
    }

    public void showAllPilots(){
        System.out.print("");
    }
}
