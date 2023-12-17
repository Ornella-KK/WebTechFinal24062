package ticketbooking.TicketBookingApp.DTO;

import lombok.Data;

import java.time.LocalDate;
public class BookingRequest {
    private String name;
    private LocalDate dateOfBirth;
    private Integer passportId;
    private String gender;
    private String contactInfo;

    public BookingRequest() {
    }

    public BookingRequest(String name, LocalDate dateOfBirth, Integer passportId, String gender, String contactInfo) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.passportId = passportId;
        this.gender = gender;
        this.contactInfo = contactInfo;
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
}
