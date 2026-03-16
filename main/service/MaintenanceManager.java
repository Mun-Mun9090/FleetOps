package main.service;

import main.model.MaintenanceRequest;
import main.util.EmergencyPriorityQueue;

public class MaintenanceManager {

    EmergencyPriorityQueue emergencyQueue = new EmergencyPriorityQueue();

    public void reportIssue(String vehicleID, String issue, int priority) {

        MaintenanceRequest req = new MaintenanceRequest(vehicleID, issue, priority);
        emergencyQueue.addRequest(req);
    }

    public void handleEmergency() {
        emergencyQueue.processRequest();
    }
}