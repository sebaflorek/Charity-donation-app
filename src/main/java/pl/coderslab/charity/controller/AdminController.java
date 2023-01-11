package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.dto.UserDetailsDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.security.CurrentUser;
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

    @GetMapping("/user/edit/{id}")
    public String editUserForm(Model model, @PathVariable Long id) {
        UserEditDto userEditDto = userMapper.userToUserEditDto(userService.findById(id));
        model.addAttribute("userEditDto", userEditDto);
        return "admin-userEdit";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-userEdit";
        }
        userService.update(userEditDto);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        userService.updateUserStatus(id, 0);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/enable/{id}")
    public String enableUser(@PathVariable Long id) {
        userService.updateUserStatus(id, 1);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        /* PREVIOUS VERSION
        if (donationService.existsDonationByUserId(id)) {
            String resultMsg = "Nie można usunąć Użytkownika, który ma zarejestrowane datki. Aby usunąć użytkownika, usuń pierw jego datki.";
            model.addAttribute("resultMsg", resultMsg);
            return "admin-message";
        }
        */
        userService.deleteById(id);
        return "redirect:/admin/user/list";
    }

    /* ================= ADMIN MANAGEMENT ================= */
    @GetMapping("/list")
    public String getAdminList(Model model) {
        List<UserDetailsDto> users = userService.findAllAdmins()
                .stream()
                .map(userMapper::userToUserDetailsDto)
                .collect(Collectors.toList());
        model.addAttribute("userList", users);
        return "admin-list";
    }

    @GetMapping("/add")
    public String addAdminForm(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "admin-userAdd";
    }

    @PostMapping("/add")
    public String addAdmin(@Valid UserCreateDto userCreateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin-userAdd";
        }
        userService.createAdmin(userCreateDto);
        return "redirect:/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String editAdminForm(Model model, @PathVariable Long id) {
        UserEditDto userEditDto = userMapper.userToUserEditDto(userService.findById(id));
        model.addAttribute("userEditDto", userEditDto);
        return "admin-edit";
    }

    @PostMapping("/edit/{id}")
    public String editAdmin(@Valid UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-edit";
        }
        userService.update(userEditDto);
        return "redirect:/admin/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (id.equals(currentUser.getUser().getId())) {
            String resultMsg = "Jesteś administratorem! Nie możesz usunąć samego siebie.";
            model.addAttribute("resultMsg", resultMsg);
            return "admin-message";
        }
        userService.deleteById(id);
        return "redirect:/admin/list";
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
