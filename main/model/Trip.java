package main.model;

public class Trip {

    private String tripID;
    private String vehicleID;
    private String driverID;
    private String routeID;
    private int fuelUsed;
    private boolean completed;

    public Trip(String tripID, String vehicleID, String driverID, String routeID, int fuelUsed) {

        this.tripID = tripID;
        this.vehicleID = vehicleID;
        this.driverID = driverID;
        this.routeID = routeID;
        this.fuelUsed = fuelUsed;
        this.completed = false;

    }

    public String getTripID() {
        return tripID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void completeTrip() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void display() {

        System.out.println(
                tripID + " | Vehicle: " + vehicleID +
                        " | Driver: " + driverID +
                        " | Route: " + routeID +
                        " | Fuel: " + fuelUsed +
                        " | Completed: " + completed
        );

    }

}