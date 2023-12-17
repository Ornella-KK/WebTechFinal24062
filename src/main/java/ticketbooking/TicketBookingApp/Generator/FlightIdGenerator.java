package ticketbooking.TicketBookingApp.Generator;

import java.io.*;
public class FlightIdGenerator {
    private static int nextId;

    static {
        loadNextId();
    }

    public static synchronized String generateFlightId() {
        String flightId = "FGT" + String.format("%03d", nextId);
        nextId++;
        saveNextId();
        return flightId;
    }

    private static void loadNextId() {
        try (BufferedReader reader = new BufferedReader(new FileReader("nextId.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                nextId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions, e.g., use a default value
            nextId = 1;
        }
    }

    private static void saveNextId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nextId.txt"))) {
            writer.write(Integer.toString(nextId));
        } catch (IOException e) {
            // Handle exceptions, e.g., log an error
            e.printStackTrace();
        }
    }
}
