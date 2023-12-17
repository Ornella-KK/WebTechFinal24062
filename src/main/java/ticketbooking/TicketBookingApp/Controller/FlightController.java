package ticketbooking.TicketBookingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ticketbooking.TicketBookingApp.Model.Flights;
import ticketbooking.TicketBookingApp.Model.UserEntity;
import ticketbooking.TicketBookingApp.Service.BookingService;
import ticketbooking.TicketBookingApp.Service.FlightService;
import ticketbooking.TicketBookingApp.Service.PassengerService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/Auth")
public class FlightController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private FlightService flightsService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PassengerService passengerService;
    @GetMapping("/searchForm")
    public String userSearchFlight(Model model){
        Flights flights = new Flights();
        model.addAttribute("flightSearch",flights);
        return "UserFlightSearch";
    }
    @PostMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam String flightDate,
                                Model model) {

        if (origin.isEmpty() || flightDate.isEmpty()) {
            model.addAttribute("error", "Origin and Flight Date must not be empty");
            return "redirect:/Auth/searchForm";
        }

        try {
            LocalDate date = LocalDate.parse(flightDate);
            List<Flights> availableFlights = flightsService.getAvailableFlights(origin, destination, date);
            model.addAttribute("flights", availableFlights);
            model.addAttribute("noFlights", availableFlights.isEmpty());
            return "UserSearchResults";
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Invalid date format");
            return "redirect:/Auth/searchForm";
        }
    }

    @GetMapping("/searchz")
    public String searchFlightByDForm(Model model){
        Flights flight = new Flights();
        model.addAttribute("flightSearchD",flight);
        return "UserSearchByDest";
    }
    @PostMapping("/searchs")
    public String searchFlightsByD(@RequestParam String destination, Model model) {
        List<Flights> availableFlightsByD = flightsService.getAvailableFlightsByD(destination);
        model.addAttribute("flights", availableFlightsByD);
        model.addAttribute("noFlights", availableFlightsByD.isEmpty());
        return "UserSearchResults";
    }

    @GetMapping("/new")
    public String createFlightForm(Model model){
        Flights flight = new Flights();
        model.addAttribute("flight",flight);
        return "AdminSaveFlight";
    }
    @PostMapping(value = "/saveFlights")
    public String createFlights(@ModelAttribute("flight") Flights flights, RedirectAttributes redirectAttributes) {
        String message = flightsService.saveFlight(flights);
        if (message != null) {
            redirectAttributes.addFlashAttribute("successMessage", message);
            return "redirect:/Auth/new";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Flights Not Saved");
            return "redirect:/Auth/new"; // Redirect to an appropriate error page or handle it in your front-end logic
        }
    }
    @GetMapping("/flightes")
    public String listFlightTable(Model model) {
        model.addAttribute("flightes", flightsService.listFlights());
        return "UserAllFlights";
    }

}
