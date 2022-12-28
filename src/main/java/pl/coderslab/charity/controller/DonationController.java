package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app/donation")
@RequiredArgsConstructor
public class DonationController {

    @GetMapping("/form")
    public String donationForm() {
        return "app-donationForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String donationAdd() {
        return "Sukces";
    }
}
