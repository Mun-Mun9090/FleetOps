
package main.service;

import java.util.PriorityQueue;
import main.model.IncidentReport;

public class IncidentManager {

    PriorityQueue<IncidentReport> incidents =
            new PriorityQueue<>((a,b) -> b.getSeverity() - a.getSeverity());

    public void reportIncident(IncidentReport report) {

        incidents.offer(report);
        System.out.println("Incident Reported.");
    }

    public void handleIncident() {

        if(incidents.isEmpty()){
            System.out.println("No incidents.");
            return;
        }

        IncidentReport r = incidents.poll();

        System.out.println("Handling Incident:");
        r.display();
    }


    public void reportIncident(String iid, String iv, String id, String desc, int sev) {

        IncidentReport report = new IncidentReport(iid, iv, id, desc, sev);

        incidents.offer(report);

        System.out.println("Incident reported successfully.");
    }
}

