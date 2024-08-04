package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CoupleOfInstitutions;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    public final InstitutionService institutionService;

    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.findAll();
        CoupleOfInstitutions couplesList = new CoupleOfInstitutions();
        model.addAttribute("coupleOfInstitutionsList",
                couplesList.getCoupleOfInstitutionsList(institutions));
        return "index";
    }
}
