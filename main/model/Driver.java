package main.model;

public class Driver {

    private String driverID;
    private String name;
    private boolean available;
    private String assignedVehicleID; // NEW: track vehicle assignment

    public Driver(String driverID, String name) {
        this.driverID = driverID;
        this.name = name;
        this.available = true;
        this.assignedVehicleID = null;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        this.available = status;
    }

    public String getAssignedVehicleID() {
        return assignedVehicleID;
    }

    public void setAssignedVehicleID(String vehicleID) {
        this.assignedVehicleID = vehicleID;
    }

    public void display() {
        System.out.println(driverID + " | " + name +
                " | Available: " + available +
                " | Assigned Vehicle: " + (assignedVehicleID != null ? assignedVehicleID : "None"));
    }
}
