package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Role;
import ru.kirsenko.InternetShop.repositories.UserRepository;
import ru.kirsenko.InternetShop.models.User;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser (User user)
    {
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Saving new User with email: {}", email);
        return true;
    }
    public List<User> list()
    {
        return userRepository.findAll();
    }
    public User show(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }
    //Функция изменения пароля
    public void changeUserPassword(User user, String password)
    {
        user.setPassword(passwordEncoder.encode(password));
    }
    public void save(User user)
    {
        userRepository.save(user);
    }
}
