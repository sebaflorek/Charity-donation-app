package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.UserDetailsDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DonationService donationService;
    private final UserService userService;
    private final InstitutionService institutionService;
    private final UserMapper userMapper;

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

    @GetMapping("/institution/add")
    public String addInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin-institutionForm";
    }

    @PostMapping("/institution/add")
    public String addInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-institutionForm";
        }
        institutionService.save(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/edit/{id}")
    public String editInstitutionForm(Model model, @PathVariable Long id) {
        Institution toEditInstitution = institutionService.findById(id);
        model.addAttribute("institution", toEditInstitution);
        return "admin-institutionForm";
    }

    @PostMapping("/institution/edit/{id}")
    public String editInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-institutionForm";
        }
        institutionService.update(institution);
        return "redirect:/admin/institution/list";
    }

    @RequestMapping("institution/delete/{id}")
    public String deleteInstitution(Model model, @PathVariable Long id) {
        if (donationService.existsDonationByInstitutionId(id)) {
            String resultMsg = "Nie można usunąć Instytucji. Instytucja jest przypisana do datku.";
            model.addAttribute("resultMsg", resultMsg);
            return "admin-message";
        }
        institutionService.deleteById(id);
        return "redirect:/admin/institution/list";
    }

    /* ================= DONATION MANAGEMENT ================= */
    @GetMapping("/donation/list")
    public String getDonationList(Model model) {
        List<Donation> donations = donationService.findAll(); // chwilowo bez DTO ze wszystkimi polami
        model.addAttribute("donationList", donations);
        return "admin-donationList";
    }

    /* ================= USER MANAGEMENT ================= */
    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<UserDetailsDto> users = userService.findAllRegularUsers()
                .stream()
                .map(userMapper::userToUserDetailsDto)
                .collect(Collectors.toList());
        model.addAttribute("userList", users);
        return "admin-userList";
    }

    /* ================= ADMIN MANAGEMENT ================= */
    @GetMapping("/list")
    public String getAdminList(Model model) {
        List<UserDetailsDto> users = userService.findAllAdmins()
                .stream()
                .map(userMapper::userToUserDetailsDto)
                .collect(Collectors.toList());
        model.addAttribute("userList", users);
        return "admin-adminList";
    }

    /* ================= END - MANAGEMENT  ================= */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/";
    }


    /* ================= TESTY ================= */
    @RequestMapping("/test/{id}")
    @ResponseBody
    public String test(@PathVariable Long id) {
        return "By Institution " + donationService.existsDonationByInstitutionId(id)
                + "<br>By Category " + donationService.existsDonationByCategoryId(id);
    }
}
