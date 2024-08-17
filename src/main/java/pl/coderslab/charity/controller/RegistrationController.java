package pl.coderslab.charity.controller;

import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.domain.Authority;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.service.AuthorityService;
import pl.coderslab.charity.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class RegistrationController {

    public final UserService userService;
    private final AuthorityService authorityService;

    public RegistrationController(UserService userService, AuthorityService authorityService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult result, Model model) {
        if(!user.getPassword().equals(user.getPassword2())) {
            result.rejectValue("password", "error.user", "Hasła nie są zgodne");
        }
        if(userService.findUserByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Już jest taki email, wybierz inny");
        }
        if (result.hasErrors()) {
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Optional<Authority> authority = authorityService.findByName("ROLE_USER");
        if (authority.isPresent()) {
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authority.get());
            user.setAuthorities(authorities);
            userService.saveUser(user);
            logger.info("Użytkownik zarejestrowany z rolą 'ROLE_USER': {}", user.getEmail());
        } else {
            throw new RuntimeException("Authority 'ROLE_USER' not found");
        }

        model.addAttribute("message", "Konto zostało założone, możesz się zalogować");
        model.addAttribute("user", new User());
        return "login";
    }
}
