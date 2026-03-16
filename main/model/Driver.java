package main.model;

public class Driver {

    private String driverID;
    private String name;
    private boolean available;

    public Driver(String driverID, String name) {
        this.driverID = driverID;
        this.name = name;
        this.available = true;
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

    public void display() {
        System.out.println(driverID + " | " + name + " | Available: " + available);
    }
}