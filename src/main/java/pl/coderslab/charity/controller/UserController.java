package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.dto.UserReadDto;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public String getUserDetails(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserReadDto userReadDto = userMapper.userToUserReadDto(userService.findById(currentUser.getUser().getId()));
        model.addAttribute("userReadDto", userReadDto);
        return "app-userDetails";
    }

    @GetMapping("/edit")
    public String editUserForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserEditDto userEditDto = userMapper.userToUserEditDto(userService.findById(currentUser.getUser().getId()));
        model.addAttribute("userEditDto", userEditDto);
        return "app-userEdit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", result.hasErrors());
            return "app-userEdit";
        }
        userService.update(userEditDto);
        return "redirect:/app/user/profile";
    }

}
