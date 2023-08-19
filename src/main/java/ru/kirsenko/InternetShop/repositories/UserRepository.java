package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.User;

public interface UserRepository extends JpaRepository <User, Long>{
    User findByEmail(String email);
}
