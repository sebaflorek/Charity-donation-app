package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.UserChangePassDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.dto.UserReadDto;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /* ================= USER READ ================= */
    @GetMapping("/profile")
    public String getUserDetails(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserReadDto userReadDto = userMapper.userToUserReadDto(userService.findById(currentUser.getUser().getId()));
        model.addAttribute("userReadDto", userReadDto);
        return "app-userDetails";
    }

    /* ================= USERS MANAGEMENT ================= */
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

    @GetMapping("/change-password")
    private String changePassForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserChangePassDto userChangePassDto = new UserChangePassDto();
        userChangePassDto.setId(currentUser.getUser().getId());
        model.addAttribute("userChangePassDto", userChangePassDto);
        return "app-userPasswordEdit";
    }

    @PostMapping("/change-password")
    private String changePass(@Valid UserChangePassDto userChangePassDto, BindingResult result, Model model,
                              HttpServletRequest request, HttpServletResponse response, Authentication authentication,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", result.hasErrors());
            return "app-userPasswordEdit";
        }
        userService.updatePassword(userChangePassDto);
        new SecurityContextLogoutHandler().logout(request, null, null);

        /* TESTY */
//        SecurityContextLogoutHandler sec = new SecurityContextLogoutHandler();
//        sec.setClearAuthentication(true);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        currentUser.eraseCredentials();
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        sec.logout(request, response, authentication);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        try {
//            request.logout();
//            request.getSession().invalidate();
//        } catch (ServletException e) {
//            throw new RuntimeException(e);
//        }
        /* KONIEC TESTÓW */

        String resultMsg = "Hasło zostało zmienione. Zaloguj się ponownie używając nowego hasła.";
        model.addAttribute("resultMsg", resultMsg);
        return "messageView";
    }

}
