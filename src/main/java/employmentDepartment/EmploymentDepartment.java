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
    public void hirePilot(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter some info: ");
        String someInfo = input.nextLine();
        System.out.println(someInfo);
    }
}
