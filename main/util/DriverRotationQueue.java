
package main.util;

import java.util.LinkedList;
import java.util.Queue;
import main.model.Driver;

public class DriverRotationQueue {

    private Queue<Driver> driverQueue = new LinkedList<>();

    // Add Driver
    public void addDriver(Driver d) {

        // Prevent duplicate driver IDs
        for (Driver driver : driverQueue) {
            if (driver.getDriverID().equals(d.getDriverID())) {
                System.out.println("Driver ID already exists.");
                return;
            }
        }

        driverQueue.offer(d);
        System.out.println("Driver added successfully.");
    }

    // Assign next available driver (Round Robin)
    public Driver getNextDriver() {

        int size = driverQueue.size();

        for (int i = 0; i < size; i++) {

            Driver d = driverQueue.poll();

            if (d.isAvailable()) {

                d.setAvailable(false);
                driverQueue.offer(d);
                return d;
            }

            driverQueue.offer(d);
        }

        return null;
    }


    public void releaseDriver(String driverID) {

        for (Driver d : driverQueue) {

            if (d.getDriverID().equals(driverID)) {

                d.setAvailable(true);
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

    // Remove driver (used by Undo)
    public void removeDriver(String driverID) {

        boolean removed = driverQueue.removeIf(d -> d.getDriverID().equals(driverID));

        if (removed) {
            System.out.println("Driver removed successfully: " + driverID);
        } else {
            System.out.println("Driver not found: " + driverID);
        }
    }
}

