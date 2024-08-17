package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CoupleOfInstitutions;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    public final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.findAll();
        CoupleOfInstitutions couplesList = new CoupleOfInstitutions();
        model.addAttribute("coupleOfInstitutionsList",
                couplesList.getCoupleOfInstitutionsList(institutions));

        List<Integer> bags = donationService.getAllBags();
        int totalBags = bags.stream().mapToInt(b -> b).sum();
        model.addAttribute("totalBags", totalBags);
        int totalDonations = bags.size();
        model.addAttribute("totalDonations", totalDonations);
        return "index";
    }

    @ModelAttribute("user")
    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
            Optional<User> user = userService.findUserByEmail(username);
            if (user.isPresent()) {
                return user.get();
            }
        }
        return null;
    }
}
