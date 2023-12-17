package ticketbooking.TicketBookingApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketbooking.TicketBookingApp.Model.Passenger;
import ticketbooking.TicketBookingApp.Repository.PassengerRepo;

@Service
public class PassengerService {
    private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    private PassengerRepo passengerRepo;
    public String savePassenger(Passenger passenger){
        if (passenger != null) {
            if (passengerAlreadyExists(passenger.getPassengerId())) {
                logger.info("Passenger already exists");
                return "Passenger already exists";
            } else {
                passengerRepo.save(passenger);
                logger.info("Passenger saved successfully");
                return "Passenger saved successfully";
            }
        } else {
            return null;
        }
    }
    public boolean passengerAlreadyExists(String passengerId) {
        return passengerRepo.existsById(passengerId);
    }
}
