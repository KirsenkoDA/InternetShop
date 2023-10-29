package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.repositories.SalesTableRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesTableService {
    private final SalesTableRepository salesTableRepository;
    public Page<SalesTable> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findAll(pageable);
    }
}
