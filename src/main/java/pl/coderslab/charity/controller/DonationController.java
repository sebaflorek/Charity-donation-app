package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/donation")
@RequiredArgsConstructor
public class DonationController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("institutionList")
    public List<Institution> getInstitutions() {
        return institutionService.findAll();
    }

    @ModelAttribute("categoryList")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
    }

    /* ================= DONATION READ ================= */


    /* ================= DONATION MANAGEMENT ================= */
    @GetMapping("/form")
    public String donationForm(Model model) {
        model.addAttribute("donation", new DonationCreateDto());
        return "app-donationForm";
    }

    @PostMapping("/form")
    public String donationAdd(@ModelAttribute("donation") @Valid DonationCreateDto donationCreateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", result.hasErrors());
            return "app-donationForm";
        }
        donationService.saveWithDto(donationCreateDto);
        return "app-donationFormConfirm";
    }
}
