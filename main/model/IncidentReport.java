package main.model;

public class IncidentReport {

    private String incidentID;
    private String vehicleID;
    private String driverID;
    private String description;
    private int severity;

    public IncidentReport(String incidentID, String vehicleID,
                          String driverID, String description, int severity) {

        this.incidentID = incidentID;
        this.vehicleID = vehicleID;
        this.driverID = driverID;
        this.description = description;
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }

    public void display() {

        System.out.println("Incident: " + incidentID +
                " Vehicle: " + vehicleID +
                " Driver: " + driverID +
                " Issue: " + description +
                " Severity: " + severity);
    }
}