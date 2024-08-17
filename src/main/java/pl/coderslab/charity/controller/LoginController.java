package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.Authority;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.service.AuthorityService;
import pl.coderslab.charity.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class LoginController {

    public final UserService userService;
    private final AuthorityService authorityService;

    public LoginController(UserService userService, AuthorityService authorityService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @RequestMapping("/login_error")
    public String loginFormError(Model model) {
        model.addAttribute("message", "Nieprawidłowy Email lub Hasło, spróbuj ponownie.");
        return "login";
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

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final BCryptPasswordEncoder passwordEncoder;


}
