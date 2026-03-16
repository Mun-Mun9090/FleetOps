package main.service;

import main.model.FuelLog;
import java.util.ArrayList;

public class FuelManager {

    private ArrayList<FuelLog> logs = new ArrayList<>();

    public void addFuelLog(String vehicleID, int fuelAmount, int distance) {
        FuelLog log = new FuelLog(vehicleID, fuelAmount, distance);
        logs.add(log);
        System.out.println("Fuel log added successfully.");
    }

    public void showFuelLogs() {
        if (logs.isEmpty()) {
            System.out.println("No fuel logs available.");
            return;
        }
        System.out.println("\n--- Fuel Logs ---");
        for (FuelLog log : logs) {
            log.display();
        }
    }
}