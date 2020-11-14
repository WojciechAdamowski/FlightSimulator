package employmentDepartment;

public class PilotsDepartment extends EmploymentDepartment{
    private static PilotsDepartment single_instance = null;

    public static PilotsDepartment getInstance()
    {
        if (single_instance == null)
            single_instance = new PilotsDepartment();

        return single_instance;
    }
}
