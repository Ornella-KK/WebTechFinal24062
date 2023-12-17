package ticketbooking.TicketBookingApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ticketbooking.TicketBookingApp.Generator.PassengerIdGenerator;

import java.time.LocalDate;
@Entity
public class Passenger {
    @Id
    private String passengerId;
    private String name;
    private LocalDate dateOfBirth;
    private Integer passportId;
    private String gender;
    private String contactInfo;
    @Column(name = "booking_id")
    private String bookingId;
    private Integer userId;

    // Default constructor required by Hibernate
    public Passenger() {
        this.passengerId = PassengerIdGenerator.generatePassengerId();
    }

    public Passenger(String name, LocalDate dateOfBirth, Integer passportId, String gender, String contactInfo, String bookingId, Integer userId) {
        this.passengerId = PassengerIdGenerator.generatePassengerId();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.passportId = passportId;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.bookingId = bookingId;
        this.userId = userId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPassportId() {
        return passportId;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
