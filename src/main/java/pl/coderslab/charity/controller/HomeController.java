package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

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
    public String registerUser(@Valid UserCreateDto userCreateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", result.hasErrors());
            return "userRegister";
        }
        model.addAttribute("registeredUser", userCreateDto.getUsername());
        userService.createUser(userCreateDto);
        return "userRegisterConfirm";
    }

}
