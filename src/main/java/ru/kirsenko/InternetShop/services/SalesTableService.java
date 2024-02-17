package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.models.Status;
import ru.kirsenko.InternetShop.models.User;
import ru.kirsenko.InternetShop.repositories.SalesTableRepository;
import ru.kirsenko.InternetShop.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesTableService {
    private final SalesTableRepository salesTableRepository;
    private final UserRepository userRepository;
    public Page<SalesTable> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findAll(pageable);
    }
    public Page<SalesTable> findPaginatedByUser(int pageNo, int pageSize, User user)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findAllByUser(pageable, user);
    }
    public Page<SalesTable> findByUser(int pageNo, int pageSize, User user)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findByUser(pageable, user);
    }
    public SalesTable findByStatusAndUser(Status status, User user)
    {
        return salesTableRepository.findByStatusAndUser(status, user);
    }
    public void save(SalesTable salesTable){
        salesTableRepository.save(salesTable);
    }
    public SalesTable show(Long id)
    {
        SalesTable salesTable = salesTableRepository.findById(id).orElse(null);
        return  salesTable;
    }
}
