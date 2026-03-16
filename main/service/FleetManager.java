
package main.service;

import main.model.Vehicle;
import java.util.HashMap;

public class FleetManager {

    private HashMap<String, Vehicle> vehicles = new HashMap<>();
    private int vehicleCounter = 1;   // counter for auto ID generation

    public void addVehicle(Vehicle v) {
        vehicles.put(v.getVehicleID(), v);
    }

    public Vehicle getVehicle(String id) {
        return vehicles.get(id);
    }

    public void showVehicles() {

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (Vehicle v : vehicles.values()) {
            v.display();
        }
    }

    public String generateVehicleID() {
        return "V" + vehicleCounter++;
    }

    public void removeVehicle(String vehicleID) {

        if(vehicles.containsKey(vehicleID)) {

            vehicles.remove(vehicleID);
            System.out.println("Vehicle removed successfully: " + vehicleID);

        } else {

            System.out.println("Vehicle not found: " + vehicleID);
        }
    }
}

