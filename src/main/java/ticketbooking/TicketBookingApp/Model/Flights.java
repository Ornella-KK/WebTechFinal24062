package ticketbooking.TicketBookingApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import ticketbooking.TicketBookingApp.Generator.FlightIdGenerator;

import java.sql.Time;
import java.time.LocalDate;
@Entity
public class Flights {
    @Id
    private String flightId;
    private String origin;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private String departureTime;
    private LocalDate flightDate;
    @Column(columnDefinition = "VARCHAR(255) default 'active'")
    private String status;

    public Flights() {
        this.flightId = FlightIdGenerator.generateFlightId();
    }

    public Flights(String flightId, String origin, String destination, int totalSeats, int availableSeats, String departureTime, LocalDate flightDate, String status) {
        this.flightId = FlightIdGenerator.generateFlightId();
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.departureTime = departureTime;
        this.flightDate = flightDate;
        this.status = status;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
