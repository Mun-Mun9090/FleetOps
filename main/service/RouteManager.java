package main.service;

import java.util.*;
import main.model.Route;

public class RouteManager {

    private Map<String, List<String>> graph = new HashMap<>();  // stop -> connected stops
    private Map<String, List<String>> routes = new HashMap<>(); // routeID -> random stops

    // Add a route with automatic random stops
    public void addRoute(String routeID) {
        String[] possibleStops = {"X", "Y", "Z", "A", "B", "C", "D", "E"};
        Random rand = new Random();
        String[] stops = new String[3]; // 3 random stops

        for (int i = 0; i < 3; i++) {
            stops[i] = possibleStops[rand.nextInt(possibleStops.length)];
        }

        routes.put(routeID, Arrays.asList(stops));

        // Connect consecutive stops in graph
        for (int i = 0; i < stops.length - 1; i++) {
            addConnection(stops[i], stops[i + 1]);
        }

        System.out.println("Route added: " + routeID + " with stops " + Arrays.toString(stops));
    }

    // Add connection (undirected)
    private void addConnection(String from, String to) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        if (!graph.get(from).contains(to)) graph.get(from).add(to);
        if (!graph.get(to).contains(from)) graph.get(to).add(from);
    }

    // Display all routes (original random routes)
    public void showRoutes() {
        if (routes.isEmpty()) {
            System.out.println("No routes available.");
            return;
        }
        for (String routeID : routes.keySet()) {
            System.out.println("Route " + routeID + ": " + String.join(" -> ", routes.get(routeID)));
        }
    }

    // Compute shortest path (fewest stops) between two stops using BFS
    public void shortestPath(String start, String end) {
        List<String> path = getShortestPath(start, end);
        if (path.isEmpty()) {
            System.out.println("No path found between " + start + " and " + end);
        } else {
            System.out.println("Shortest path: " + String.join(" -> ", path));
            System.out.println("Stops count: " + path.size());
        }
    }

    // Return shortest path as list of stops
    public List<String> getShortestPath(String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) return new ArrayList<>();

        Map<String, String> prev = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) break;

            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (!prev.containsKey(end) && !start.equals(end)) return new ArrayList<>();

        List<String> path = new ArrayList<>();
        String at = end;
        path.add(at);
        while (prev.containsKey(at)) {
            at = prev.get(at);
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}