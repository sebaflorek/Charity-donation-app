package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.UserChangePassDto;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.dto.UserForgotPassDto;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DonationRepository donationRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public void save(User user) {
        userRepository.save(user);
    }

    public void createUser(UserCreateDto userCreateDto, HttpServletRequest request) {
        User user = new User();
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setUsername(userCreateDto.getUsername());
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getSurname());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEmail(userCreateDto.getEmail());
        String token = RandomString.make(30);
        user.setToken(token);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
        sendActivationLink(userCreateDto.getEmail(), token, request);
    }

    public void createAdmin(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getSurname());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEmail(userCreateDto.getEmail());
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userRepository.save(user);
    }

    public void activateUser(User user) {
        user.setEnabled(1);
        user.setToken(null);
        userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllRegularUsers() {
        return userRepository.findAllByRolesName("ROLE_USER").stream().filter(user -> user.getRoles().size() == 1).collect(Collectors.toList());
    }

    public List<User> findAllAdmins() {
        return userRepository.findAllByRolesName("ROLE_ADMIN");
    }

    public void update(UserEditDto userEditDto) {
        User user = userRepository.findById(userEditDto.getId()).orElse(null);
        if (user != null) {
            userMapper.userEditDtoToUserEntity(user, userEditDto);
            userRepository.save(user);
        }
    }

    public void updatePassword(UserChangePassDto userChangePassDto) {
        User user = userRepository.findById(userChangePassDto.getId()).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(userChangePassDto.getNewPassword()));
            userRepository.save(user);
        }
    }

    public void resetPassword(UserForgotPassDto userForgotPassDto, HttpServletRequest request) {
        String email = userForgotPassDto.getEmail();
        User user = userRepository.findUserByEmail(email);
        String token = RandomString.make(30);
        user.setToken(token);
        userRepository.save(user);
        sendResetPasswordLink(user, request);
    }

    public void setNewAfterResetPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setToken(null);
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        donationRepository.updateDonationUser(id, null);
        userRepository.deleteById(id);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void updateUserStatus(Long id, int status) {
        userRepository.updateUserStatus(id, status);
    }

    private void sendActivationLink(String email, String token, HttpServletRequest request) {
        User user = userRepository.findUserByEmail(email);
        String url = request.getRequestURL().toString();
        String link = url.replace(request.getServletPath(), "");
        String activateAccLink = link + "/activate-account?token=" + token;
        String title = "W Dobre Ręce: aktywuj konto";
        String message = "Cześć " + user.getUsername().toUpperCase() + ",\n" +
                "\nPoniżej przesyłamy link do aktywacji konta.\n" +
                "\n" + activateAccLink + "\n" +
                "\nPozdrawiamy,\n" +
                "Zespół \"Oddam w dobre ręce\"";
        emailService.sendSimpleEmail(email, title, message);
    }

    private void sendResetPasswordLink(User user, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String link = url.replace(request.getServletPath(), "");
        String resetPassLink = link + "/reset-password?token=" + user.getToken();
        String title = "W Dobre Ręce: reset hasła";
        String message = "Cześć " + user.getUsername().toUpperCase() + ",\n" +
                "\nPoniżej przesyłamy link do zresetowania Twojego hasła.\n" +
                "\n" + resetPassLink + "\n" +
                "\nPozdrawiamy,\n" +
                "Zespół \"Oddam w dobre ręce\"";
        emailService.sendSimpleEmail(user.getEmail(), title, message);

    }


}
