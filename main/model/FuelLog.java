package main.model;

public class FuelLog {

    private String vehicleID;
    private int fuelAmount;
    private int distance;

    public FuelLog(String vehicleID, int fuelAmount, int distance) {
        this.vehicleID = vehicleID;
        this.fuelAmount = fuelAmount;
        this.distance = distance;
    }

    public void display() {

        System.out.println("Vehicle: " + vehicleID +
                " Fuel: " + fuelAmount +
                " Distance: " + distance +
                " Efficiency: " + (distance / fuelAmount));
    }
}