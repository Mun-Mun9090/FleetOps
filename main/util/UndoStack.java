
package main.util;

import java.util.Stack;
import main.service.FleetManager;
import main.service.TripManager;
import main.util.DriverRotationQueue;

public class UndoStack {

    private Stack<String> stack;

    public UndoStack() {
        stack = new Stack<>();
    }

    public void push(String action) {
        stack.push(action);
    }

    public void undo(FleetManager fleet, TripManager trips, DriverRotationQueue drivers) {

        if(stack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        String action = stack.pop();

        String parts[] = action.split(" ");

        if(parts[0].equals("VehicleAdded")) {

            String vehicleID = parts[1];
            fleet.removeVehicle(vehicleID);

            System.out.println("Undo successful: Vehicle removed -> " + vehicleID);
        }

        else if(parts[0].equals("DriverAdded")) {

            String driverID = parts[1];
            drivers.removeDriver(driverID);

            System.out.println("Undo successful: Driver removed -> " + driverID);
        }

        else if(parts[0].equals("TripProcessed")) {

            String tripID = parts[1];
            trips.removeTrip(tripID);

            System.out.println("Undo successful: Trip removed -> " + tripID);
        }

        else {

            System.out.println("Undo: " + action);
        }
    }
}

