package main.model;

public class Route {

    private String routeID;
    private String[] stops;

    public Route(String routeID, String[] stops) {
        this.routeID = routeID;
        this.stops = stops;
    }

    public String getRouteID() {
        return routeID;
    }

    public void displayRoute() {

        System.out.print("Route " + routeID + ": ");

        for (String stop : stops) {
            System.out.print(stop + " -> ");
        }

        System.out.println("END");
    }
}