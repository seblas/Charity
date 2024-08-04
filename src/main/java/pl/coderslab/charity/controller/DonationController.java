package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CoupleOfInstitutions;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
public class DonationController {

    public final InstitutionService institutionService;
    private final DonationService donationService;

    public DonationController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
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
}
