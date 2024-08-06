package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import org.hibernate.boot.jaxb.spi.Binding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private final InstitutionService institutionService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService, InstitutionService institutionService1) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService1;
    }

    @RequestMapping("")
    public String form(Model model){
        model.addAttribute("donation", new Donation());
        model.addAttribute("categoryList",categoryService.getAll());
        model.addAttribute("institutions", institutionService.findAll());
        return "form";
    }

    @PostMapping("")
    public String formAction(@Valid Donation donation, BindingResult result, Model model) {
        model.addAttribute("thanksMessage", "Dziękujemy za oddanie rzeczy!");
        donationService.save(donation);
        return "form";
    }
}
