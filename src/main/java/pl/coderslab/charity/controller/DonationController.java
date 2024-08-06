package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.Category;
import pl.coderslab.charity.domain.CoupleOfInstitutions;
import pl.coderslab.charity.domain.Donation;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@RequestMapping("/form")
@Controller
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public String formAction(Model model){
        model.addAttribute("donation", new Donation());

        model.addAttribute("categoryList",categoryService.getAll());
        return "form";
    }
}
