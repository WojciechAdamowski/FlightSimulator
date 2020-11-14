package employmentDepartment;

public class FlightAttendantsDepartment {
    private static FlightAttendantsDepartment single_instance = null;

    public static FlightAttendantsDepartment getInstance()
    {
        if (single_instance == null)
            single_instance = new FlightAttendantsDepartment();

        return single_instance;
    }
}
