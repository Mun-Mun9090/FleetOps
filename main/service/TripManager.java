
package main.service;

import main.model.Trip;

import java.util.LinkedList;
import java.util.Queue;

public class TripManager {

    private Queue<String> tripRequests = new LinkedList<>();
    private LinkedList<Trip> tripHistory = new LinkedList<>();

    public void addTripRequest(String tripID) {
        tripRequests.offer(tripID);
        System.out.println("Trip request added: " + tripID);
    }

    public String processTripRequest() {

        if (tripRequests.isEmpty()) {
            System.out.println("No trip requests.");
            return null;
        }

        return tripRequests.poll();
    }

    public void completeTrip(Trip trip) {

        tripHistory.add(trip);

        System.out.println("Trip Completed:");
        trip.display();
    }

    public void showTripHistory() {

        if(tripHistory.isEmpty()){
            System.out.println("No trips in history.");
            return;
        }

        for (Trip t : tripHistory) {
            t.display();
        }
    }

    // 🔹 ADD TRIP
    public void addTrip(Trip trip) {

        tripHistory.add(trip);
        System.out.println("Trip added successfully.");
    }

    // 🔹 FIND TRIP
    public Trip findTrip(String completeID) {

        for (Trip t : tripHistory) {

            if(t.getTripID().equals(completeID)){
                return t;
            }
        }

        return null;
    }


    public void removeTrip(String tripID) {

        if(tripHistory.isEmpty()){
            System.out.println("No trips available.");
            return;
        }

        for(Trip t : tripHistory){

            if(t.getTripID().equals(tripID)){
                tripHistory.remove(t);
                System.out.println("Trip removed successfully: " + tripID);
                return;
            }
        }

        System.out.println("Trip not found: " + tripID);
    }


}

