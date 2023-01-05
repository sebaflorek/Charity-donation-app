package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DonationService donationService;
    private final UserService userService;
    private final InstitutionService institutionService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institutionList", institutions);
        model.addAttribute("donationNum", donationService.countAllDonations());
        model.addAttribute("bagsNum", donationService.countAllBags());
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "userRegister";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserCreateDto userCreateDto, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", result.hasErrors());
            return "userRegister";
        }
        model.addAttribute("registeredUser", userCreateDto.getUsername());
        try {
            userService.createUser(userCreateDto, request);
        } catch (MailException mailException) {
            String failMsg = "Wystąpił błąd. Nie udało się zarejestrować konta. Spróbuj jeszcze raz i upewnij się, że wpisałeś poprawnego maila";
            model.addAttribute("resultMsg", failMsg);
            return "messageView";
        }
        return "userRegisterConfirm";
    }

    @RequestMapping("/activate-account")
    private String activateUser(@RequestParam(name = "token") String token, Model model) {
        User user = userService.findByToken(token);
        String message;
        if (user == null) {
            message = "Nie udało się aktywować konto. Niepoprawny lub nieaktualny link";
        } else {
            userService.activateUser(user);
            message = "Sukces! Twoje konto zostało aktywowane. Zaloguj się by w pełni korzystać z serwisu.";
        }
        model.addAttribute("resultMsg", message);
        return "messageView";
    }

    @GetMapping("/login")
    public String login() {
        return "userLogin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "userLogout";
    }

}
