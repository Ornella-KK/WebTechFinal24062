package ticketbooking.TicketBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.TicketBookingApp.Model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsermail(String usermail);
    Boolean existsByUsermail(String usermail);
}
