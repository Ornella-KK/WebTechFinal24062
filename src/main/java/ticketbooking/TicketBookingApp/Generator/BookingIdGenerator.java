package ticketbooking.TicketBookingApp.Generator;

import java.io.*;

public class BookingIdGenerator {
    private static int nextId;

    static {
        loadNextId();
    }

    public static synchronized String generateBookingId() {
        String bookingId = "BKG" + String.format("%03d", nextId);
        nextId++;
        saveNextId();
        return bookingId;
    }

    private static void loadNextId() {
        try (BufferedReader reader = new BufferedReader(new FileReader("nextBookingId.txt"))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nextBookingId.txt"))) {
            writer.write(Integer.toString(nextId));
        } catch (IOException e) {
            // Handle exceptions, e.g., log an error
            e.printStackTrace();
        }
    }
}

