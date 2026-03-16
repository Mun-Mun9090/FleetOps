package main;

import java.util.Scanner;
import main.model.Driver;
import main.model.Trip;
import main.model.Vehicle;
import main.service.*;
import main.util.DriverRotationQueue;
import main.util.UndoStack;

public class FleetOpsApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FleetManager fleet = new FleetManager();
        TripManager trips = new TripManager();
        MaintenanceManager maintenance = new MaintenanceManager();
        IncidentManager incidents = new IncidentManager();
        DriverRotationQueue drivers = new DriverRotationQueue();
        UndoStack undo = new UndoStack();
        RouteManager routeManager = new RouteManager();
        FuelManager fuelManager = new FuelManager();

        System.out.println("=======================================");
        System.out.println("   Welcome to FleetOps Management");
        System.out.println("=======================================");

        while(true) {
            System.out.println("\n===== FleetOps Menu =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Show Vehicles");
            System.out.println("3. Add Driver");
            System.out.println("4. Show Drivers");
            System.out.println("5. Add Trip Request");
            System.out.println("6. Process Trip");
            System.out.println("7. Report Breakdown");
            System.out.println("8. Handle Emergency");
            System.out.println("9. Report Incident");
            System.out.println("10. Handle Incident");
            System.out.println("11. Complete Trip");
            System.out.println("12. Undo Last Action");
            System.out.println("13. Add Route");
            System.out.println("14. Show Routes");
            System.out.println("15. Compute Shortest Path");
            System.out.println("16. Show Fuel Logs");
            System.out.println("17. Exit");

            System.out.print("Enter your choice: ");
            int choice;
            try { choice = sc.nextInt(); }
            catch(Exception e) { sc.nextLine(); System.out.println("Invalid input."); continue; }

            switch(choice) {

                case 1:
                    sc.nextLine();
                    String vid = fleet.generateVehicleID();
                    System.out.println("Generated Vehicle ID: " + vid);
                    System.out.print("Enter Vehicle Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Mileage: ");
                    int mileage = sc.nextInt();
                    fleet.addVehicle(new Vehicle(vid,type,mileage,"Active"));
                    undo.push("VehicleAdded " + vid);
                    System.out.println("Vehicle added successfully.");
                    break;

                case 2: fleet.showVehicles(); break;
                case 3:
                    System.out.print("Enter Driver ID: ");
                    String did = sc.next();
                    sc.nextLine();
                    System.out.print("Enter Driver Name: ");
                    String name = sc.nextLine();
                    drivers.addDriver(new Driver(did,name));
                    undo.push("DriverAdded " + did);
                    System.out.println("Driver added successfully.");
                    break;
                case 4: drivers.showDrivers(); break;
                case 5:
                    System.out.print("Enter Trip ID: ");
                    trips.addTripRequest(sc.next());
                    break;
                case 6:
                    String request = trips.processTripRequest();
                    if(request == null) break;
                    System.out.print("Enter Vehicle ID: ");
                    String vehicleID = sc.next();
                    Vehicle vehicle = fleet.getVehicle(vehicleID);
                    if(vehicle == null) { System.out.println("Vehicle not found."); break; }
                    Driver d = drivers.getNextDriver(vehicleID);
                    if(d == null) { System.out.println("No available drivers."); break; }
                    System.out.print("Enter Route ID: ");
                    String routeID = sc.next();
                    int stopsCount = 3;
                    int distance = stopsCount*10;
                    int fuelUsed = Math.max(1,distance/vehicle.getMileage());
                    Trip trip = new Trip(request, vehicleID,d.getDriverID(),routeID,fuelUsed);
                    trips.addTrip(trip);
                    fuelManager.addFuelLog(vehicleID,fuelUsed,distance);
                    undo.push("TripProcessed " + request);
                    System.out.println("Trip booked successfully. Driver "+d.getName()+" assigned to Vehicle "+vehicleID);
                    break;

                case 7:
                    sc.nextLine();
                    System.out.print("Enter Vehicle ID: ");
                    String vID = sc.nextLine();
                    System.out.print("Enter Issue: ");
                    String issue = sc.nextLine();
                    System.out.print("Enter Priority (1-10): ");
                    int priority = sc.nextInt();
                    maintenance.reportIssue(vID,issue,priority);
                    undo.push("BreakdownReported " + vID);
                    break;

                case 8: maintenance.handleEmergency(); break;
                case 9:
                    System.out.print("Enter Incident ID: ");
                    String iid = sc.next();
                    System.out.print("Enter Vehicle ID: ");
                    String iv = sc.next();
                    System.out.print("Enter Driver ID: ");
                    String id = sc.next();
                    sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter Severity (1-10): ");
                    int sev = sc.nextInt();
                    incidents.reportIncident(iid,iv,id,desc,sev);
                    undo.push("IncidentReported " + iid);
                    break;

                case 10: incidents.handleIncident(); break;

                case 11:
                    System.out.print("Enter Trip ID to complete: ");
                    String completeID = sc.next();
                    Trip t = trips.findTrip(completeID);
                    if(t == null) { System.out.println("Trip not found."); break; }
                    t.completeTrip();
                    drivers.releaseDriver(t.getDriverID());
                    System.out.println("Trip completed successfully.");
                    break;

                case 12: undo.undo(fleet,trips,drivers); break;

                case 13:
                    sc.nextLine();
                    System.out.print("Enter Route ID: ");
                    routeManager.addRoute(sc.nextLine());
                    break;

                case 14: routeManager.showRoutes(); break;

                case 15:
                    sc.nextLine();
                    System.out.print("Enter Start Stop: ");
                    String start = sc.nextLine();
                    System.out.print("Enter End Stop: ");
                    String end = sc.nextLine();
                    routeManager.shortestPath(start,end);
                    break;

                case 16: fuelManager.showFuelLogs(); break;

                case 17:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default: System.out.println("Invalid choice.");
            }
        }
    }
}
