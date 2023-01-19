package TeamLongestPeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entry {
    private String employeeID;
    private String projectID;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    private Entry(String employeeID, String projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeID = employeeID;
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getProjectID() {
        return projectID;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public static Entry createEntry(String[] data) {

        // Sets dateTo to current date if dateTo input is NULL
        if (data[3].trim().equals("NULL")) {
            data[3] = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        String empID = data[0].trim();
        String projID = data[1].trim();
        LocalDate dateFrom = LocalDate.parse(data[2].trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate dateTo = LocalDate.parse(data[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE);

        return new Entry(empID, projID, dateFrom, dateTo);
    }
}
