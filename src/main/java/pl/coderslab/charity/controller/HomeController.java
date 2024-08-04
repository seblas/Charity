package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import pl.coderslab.charity.service.InstitutionService;

@Controller
public class HomeController {

    public final InstitutionService institutionService;

    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }
}
