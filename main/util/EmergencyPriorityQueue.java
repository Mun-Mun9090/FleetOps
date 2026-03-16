package main.util;

import main.model.MaintenanceRequest;
import java.util.PriorityQueue;

public class EmergencyPriorityQueue {

    private PriorityQueue<MaintenanceRequest> queue = new PriorityQueue<>();

    public void addRequest(MaintenanceRequest req) {
        queue.offer(req);
    }

    public void processRequest() {

        MaintenanceRequest r = queue.poll();

        if (r == null) {
            System.out.println("No emergencies.");
            return;
        }

        System.out.println("Processing Emergency:");
        r.display();
    }
}