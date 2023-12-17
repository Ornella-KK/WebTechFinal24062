package ticketbooking.TicketBookingApp.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ticketbooking.TicketBookingApp.Model.Roles;
import ticketbooking.TicketBookingApp.Model.UserEntity;
import ticketbooking.TicketBookingApp.Repository.UserRepo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String usermail) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsermail(usermail).orElseThrow(() -> new UsernameNotFoundException("Usermail Not Found"));

        // Ensure getRoles() doesn't trigger another call to loadUserByUsername
        Collection<GrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(user.getUsermail(), user.getPassword(), authorities);
    }
    public int getUserIdByEmail(String usermail) {
        UserEntity userEntity = userRepo.findByUsermail(usermail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mail: " + usermail));

        return userEntity.getId();
    }

}
