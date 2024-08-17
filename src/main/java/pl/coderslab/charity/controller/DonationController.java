package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import org.hibernate.boot.jaxb.spi.Binding;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.domain.*;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/form")
@Controller
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService, InstitutionService institutionService1, UserService userService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService1;
        this.userService = userService;
    }

    @RequestMapping("")
    public String form(Model model){
        model.addAttribute("donation", new Donation());
        model.addAttribute("categoryList",categoryService.getAll());
        model.addAttribute("institutions", institutionService.findAll());
        return "form";
    }

    @PostMapping("")
    public String formAction(@Valid Donation donation, BindingResult result) {
        donationService.save(donation);
        return "form-confirmation";
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
