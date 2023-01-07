package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DonationService donationService;
    private final UserService userService;
    private final InstitutionService institutionService;

    @RequestMapping("/panel")
    public String showPanel(Model model) {
        model.addAttribute("userNum", userService.countUsers());
        model.addAttribute("institutionNum", institutionService.countInstitutions());
        model.addAttribute("donationNum", donationService.countAllDonations());
        model.addAttribute("bagsNum", donationService.countAllBags());
        return "admin-panel";
    }

    /* ================= INSTITUTION MANAGEMENT ================= */
    @GetMapping("/institution/list")
    public String getInstitutionList(Model model) {
        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institutionList", institutions);
        return "admin-institutionList";
    }




    /* ================= DONATION MANAGEMENT ================= */
    /* ================= USER MANAGEMENT ================= */
    /* ================= END - MANAGEMENT  ================= */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/";
    }
}
