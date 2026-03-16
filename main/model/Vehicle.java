package main.model;

public class Vehicle {

    private String vehicleID;
    private String type;
    private int mileage;
    private String status;

    public Vehicle(String vehicleID, String type, int mileage, String status) {
        this.vehicleID = vehicleID;
        this.type = type;
        this.mileage = mileage;
        this.status = status;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public int getMileage() {
        return mileage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void display() {
        System.out.println(vehicleID + " | " + type + " | Mileage: " + mileage + " | Status: " + status);
    }
}