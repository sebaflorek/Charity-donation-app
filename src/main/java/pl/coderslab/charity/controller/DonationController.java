package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/app/donation")
@RequiredArgsConstructor
public class DonationController {
    private final InstitutionService institutionService;
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

    /* ================= DONATION MANAGEMENT ================= */
    @GetMapping("/form")
    public String donationForm(Model model) {
        model.addAttribute("donation", new DonationCreateDto());
        return "app-donationForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String donationAdd() {
        return "Sukces";
    }
}
