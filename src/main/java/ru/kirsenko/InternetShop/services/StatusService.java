package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.Status;
import ru.kirsenko.InternetShop.repositories.StatusRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;
    public Status show(long id)
    {
        return statusRepository.findById(id).orElse(null);
    }
}
