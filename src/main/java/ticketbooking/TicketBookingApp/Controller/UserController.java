package ticketbooking.TicketBookingApp.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ticketbooking.TicketBookingApp.DTO.LoginDTO;
import ticketbooking.TicketBookingApp.DTO.RegisterDTO;
import ticketbooking.TicketBookingApp.Model.Roles;
import ticketbooking.TicketBookingApp.Model.UserEntity;
import ticketbooking.TicketBookingApp.Repository.RoleRepo;
import ticketbooking.TicketBookingApp.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ticketbooking.TicketBookingApp.Service.CustomUserDetailsService;
import ticketbooking.TicketBookingApp.Service.UserService;

@Controller
@RequestMapping("/Auth")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo,PasswordEncoder passwordEncoder, UserService userService,CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }
    //user registration
    /*@PostMapping(value = "/register", consumes = { "application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepo.existsByUsermail(registerDTO.getUsermail())){
            return new ResponseEntity<>("User is taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsermail(registerDTO.getUsermail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Roles roles = roleRepo.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        logger.info("Role: {}", roles);
        user.setRoles(roles.getName());
        userRepo.save(user);

        return new ResponseEntity<>("User register Successful", HttpStatus.OK);

    }*/
    @PostMapping(value = "/register")
    public String register(@ModelAttribute RegisterDTO registerDTO, RedirectAttributes redirectAttributes) {
        if (userRepo.existsByUsermail(registerDTO.getUsermail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists");
            return "redirect:/Auth/reg"; // Redirect to your registration page
        }

        UserEntity user = new UserEntity();
        user.setUsermail(registerDTO.getUsermail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Roles roles = roleRepo.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        logger.info("Role: {}", roles);
        user.setRoles(roles.getName());
        userRepo.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "User register Successful");
        return "redirect:/Auth/reg"; // Redirect to your home page or any other appropriate page
    }

    @GetMapping("/reg")
    public String userRegForm(Model model){
        UserEntity userEnt = new UserEntity();
        model.addAttribute("userReg",userEnt);
        return "UserRegistration";
    }
    //user login
    @GetMapping("/log")
    public String userLogForm(Model model){
        UserEntity userEnt = new UserEntity();
        model.addAttribute("userLog",userEnt);
        return "UserLogin";
    }
    /*@PostMapping(value = "/logIn")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User Logged In Successfully!",HttpStatus.OK);
    }*/
    /*@PostMapping(value = "/login")
    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsermail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve user ID from authentication principal
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            int userId = userService.getUserIdByEmail(userDetails.getUsername());

            // Store user ID in a cookie
            Cookie cookie = new Cookie("userId", String.valueOf(userId));
            response.addCookie(cookie);

            return "redirect:/Auth/searchForm"; // Redirect to the dashboard or any other appropriate page after successful login
        } catch (AuthenticationException e) {
            e.printStackTrace(); // Log the exception details
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid mail or password");
            return "redirect:/Auth/log"; // Redirect to the login page with an error message
        }
    }*/
    @PostMapping(value = "/login")
    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsermail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve user ID from authentication principal
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            int userId = customUserDetailsService.getUserIdByEmail(userDetails.getUsername());

            // Store user ID in a cookie
            Cookie cookie = new Cookie("userId", String.valueOf(userId));
            response.addCookie(cookie);
            return "redirect:/Auth/searchForm"; // Redirect to the dashboard or any other appropriate page after successful login
        } catch (AuthenticationException e) {
            e.printStackTrace(); // Log the exception details
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return "redirect:/Auth/log"; // Redirect to the login page with an error message
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Create a new cookie with the same name ("userId") and set its maximum age to 0
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        // Add the cookie to the response to delete it
        response.addCookie(cookie);

        // Add any other logout logic as needed

        return "redirect:/Auth/log"; // Redirect to the login page or any other appropriate page after logout
    }

}
