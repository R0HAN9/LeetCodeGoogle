import java.util.*;

public class ReconstructItinerary {

    // Main method to reconstruct the itinerary
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build a graph using a map where each airport points to a priority
        // queue of destinations
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            // Add the destination airport to the priority queue for the current airport
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
        }

        // Step 2: Initialize a LinkedList to store the itinerary in reverse order
        LinkedList<String> itinerary = new LinkedList<>();

        // Step 3: Start DFS from "JFK" to find the entire route
        dfs("JFK", graph, itinerary);

        // Step 4: Return the itinerary (since it's built in reverse, we reverse it at
        // the end)
        return itinerary;
    }

    // Helper function to perform DFS and build the itinerary
    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        // Get the priority queue of destinations for the current airport
        PriorityQueue<String> destinations = graph.get(airport);

        // While there are still destinations to visit from this airport
        while (destinations != null && !destinations.isEmpty()) {
            // Visit the lexicographically smallest destination (using min-heap)
            String next = destinations.poll();
            // Recursively visit the next airport
            dfs(next, graph, itinerary);
        }

        // After visiting all possible destinations, add the current airport to the
        // itinerary
        itinerary.addFirst(airport);
    }

    public static void main(String[] args) {
        ReconstructItinerary solution = new ReconstructItinerary();

        // Example input with a list of tickets
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO"));

        // Find the reconstructed itinerary
        List<String> result = solution.findItinerary(tickets);

        // Print the result (reconstructed itinerary)
        System.out.println(result); // Output: [JFK, ATL, JFK, SFO, ATL, SFO]
    }
}
