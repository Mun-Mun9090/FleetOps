package main.model;

public class MaintenanceRequest implements Comparable<MaintenanceRequest>{

    private String vehicleID;
    private String issue;
    private int priority;

    public MaintenanceRequest(String vehicleID, String issue, int priority) {
        this.vehicleID = vehicleID;
        this.issue = issue;
        this.priority = priority;
    }

    public int compareTo(MaintenanceRequest other) {
        return other.priority - this.priority;
    }

    public void display() {
        System.out.println(vehicleID + " | Issue: " + issue + " | Priority: " + priority);
    }
}