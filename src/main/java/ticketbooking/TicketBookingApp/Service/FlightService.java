package ticketbooking.TicketBookingApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketbooking.TicketBookingApp.Model.Flights;
import ticketbooking.TicketBookingApp.Repository.FlightRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepo flightsRepo;
    public Flights getFlightById(String flightId) {
        return flightsRepo.findById(flightId)
                .orElse(null);
    }
    public List<Flights> getAvailableFlights(String origin, String destination, LocalDate flightDate) {
        return flightsRepo.findByOriginAndDestinationAndFlightDate(origin, destination, flightDate);
    }
    public List<Flights> getAvailableFlightsByD(String destination) {
        return flightsRepo.findByDestination(destination);
    }
    public void updateAvailableSeats(String flightId, int newAvailableSeats) {
        try {
            Optional<Flights> optionalFlight = flightsRepo.findById(flightId);
            if (optionalFlight.isPresent()) {
                Flights flight = optionalFlight.get();
                flight.setAvailableSeats(newAvailableSeats);
                flightsRepo.save(flight);
            } else {
                System.err.println("Flight not found with ID: " + flightId);
            }
        } catch (Exception e) {
            // Handle other exceptions, e.g., log an error
            e.printStackTrace();
        }
    }
    public String saveFlight(Flights flights){
        if (flights != null) {
            if (flightAlreadyExists(flights.getFlightId())) {
                logger.info("Flights already exists");
                return "Flights already exists";
            } else {
                flightsRepo.save(flights);
                logger.info("Flights saved successfully");
                return "Flights saved successfully";
            }
        } else {
            return null;
        }
    }
    public boolean flightAlreadyExists(String flightId) {
        return flightsRepo.existsById(flightId);
    }

    public List<Flights> listFlights() {
        return flightsRepo.findAll();
    }

}
