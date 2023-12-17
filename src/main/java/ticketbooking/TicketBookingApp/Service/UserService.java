package ticketbooking.TicketBookingApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ticketbooking.TicketBookingApp.Model.UserEntity;
import ticketbooking.TicketBookingApp.Repository.UserRepo;

import java.util.Collections;

@Service
public class UserService {
    /*@Autowired
    private UserRepo userRepo;
    public UserDetails loadUserByUsername(String usermail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsermail(usermail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + usermail));

        return new User(userEntity.getUsermail(), userEntity.getPassword(), Collections.emptyList());
    }

    public int getUserIdByEmail(String usermail) {
        UserEntity userEntity = userRepo.findByUsermail(usermail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mail: " + usermail));

        return userEntity.getId();
    }*/
}
