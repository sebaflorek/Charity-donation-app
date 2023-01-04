package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.UserChangePassDto;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public void createUser(UserCreateDto userCreateDto) {
        User user = new User();
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setUsername(userCreateDto.getUsername());
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getSurname());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEmail(userCreateDto.getEmail());
        user.setEnabled(1); // set Enable po potwierdzaniu mailowym
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
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

    public List<User> findAll() {
        return userRepository.findAll();
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

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
