package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.repositories.CharacteristicRepository;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;
    public List<Characteristic> characteristicList() {
        return characteristicRepository.findAll();
    }
    public void delete(long id)
    {
        log.info("delete{}", id);
        characteristicRepository.deleteById(id);
    }
    public Characteristic show(long id)
    {
        return characteristicRepository.findById(id).orElse(null);
    }
    public void save(Characteristic characteristic){
        characteristicRepository.save(characteristic);
    }
}
