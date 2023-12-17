package ticketbooking.TicketBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.TicketBookingApp.Model.Roles;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
