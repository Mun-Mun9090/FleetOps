package main.util;

import java.util.LinkedList;
import java.util.Queue;
import main.model.Driver;

public class DriverRotationQueue {

    private Queue<Driver> driverQueue = new LinkedList<>();

    // Add Driver
    public void addDriver(Driver d) {
        for (Driver driver : driverQueue) {
            if (driver.getDriverID().equals(d.getDriverID())) {
                System.out.println("Driver ID already exists.");
                return;
            }
        }
        driverQueue.offer(d);
        System.out.println("Driver added successfully.");
    }

    // Assign next available driver (Round Robin) AND assign vehicle
    public Driver getNextDriver(String vehicleID) {
        int size = driverQueue.size();

        for (int i = 0; i < size; i++) {
            Driver d = driverQueue.poll();

            if (d.isAvailable()) {
                d.setAvailable(false);
                d.setAssignedVehicleID(vehicleID); // assign vehicle
                driverQueue.offer(d);
                return d;
            }

            driverQueue.offer(d);
        }

        return null; // no available drivers
    }

    // Release driver after trip completion
    public void releaseDriver(String driverID) {
        for (Driver d : driverQueue) {
            if (d.getDriverID().equals(driverID)) {
                d.setAvailable(true);
                d.setAssignedVehicleID(null); // release vehicle
                System.out.println("Driver released: " + driverID);
                return;
            }
        }
        System.out.println("Driver not found.");
    }

    // Display all drivers
    public void showDrivers() {
        if (driverQueue.isEmpty()) {
            System.out.println("No drivers available.");
            return;
        }

        for (Driver d : driverQueue) {
            d.display();
        }
    }

    // Remove driver
    public void removeDriver(String driverID) {
        boolean removed = driverQueue.removeIf(d -> d.getDriverID().equals(driverID));
        if (removed) {
            System.out.println("Driver removed successfully: " + driverID);
        } else {
            System.out.println("Driver not found.");
        }
    }
}
