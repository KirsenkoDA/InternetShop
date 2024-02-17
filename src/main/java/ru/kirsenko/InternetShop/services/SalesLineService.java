package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.SalesLine;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.models.User;
import ru.kirsenko.InternetShop.repositories.SalesLineRepository;
import ru.kirsenko.InternetShop.repositories.SalesTableRepository;
import ru.kirsenko.InternetShop.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesLineService {
    private final SalesLineRepository salesLineRepository;
    private final UserRepository userRepository;
    public Page<SalesLine> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesLineRepository.findAll(pageable);
    }
    public Page<SalesLine> findBySalesTable(int pageNo, int pageSize, SalesTable salesTable)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        var res = salesLineRepository.findBySalesTable(pageable, salesTable);
        return res;
    }
    public List<SalesLine> findBySalesTableList(SalesTable salesTable)
    {
        return salesLineRepository.findBySalesTableOrderByQuantity(salesTable);
    }
    public void save(SalesLine salesLine){
        salesLineRepository.save(salesLine);
    }
}
