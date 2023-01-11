package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.dto.DonationReadDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.mapper.DonationMapper;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app/donation")
@RequiredArgsConstructor
public class DonationController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final DonationMapper donationMapper;

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
    @GetMapping("/my-list")
    public String getMyDonations(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<DonationReadDto> donations = donationService.findAllByUserId(currentUser.getUser().getId())
                .stream()
                .map(donationMapper::donationToDonationReadDto)
                .collect(Collectors.toList());
        model.addAttribute("donationList", donations);
        return "app-userDonationList";
    }

    @GetMapping("/details/{id}")
    public String getDonationDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        Donation donation = donationService.findById(id);
        if (donation.getUser().getId().equals(currentUser.getUser().getId())) {
            model.addAttribute("donation", donationMapper.donationToDonationReadDto(donation));
            return "app-userDonationDetails";
        }
        String failMsg = "Wystąpił błąd! Nie możesz tego zrobić!";
        model.addAttribute("resultMsg", failMsg);
        return "messageView";
    }

    @GetMapping("/donated/{id}")
    public String setDonationStatus(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        donationService.setDonationReceivedStatus(id, currentUser.getUser().getId());
        return "redirect:/app/donation/details/" + id;
    }



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
