package com.artem.university.service;

import com.artem.university.database.repository.LectorRepository;
import com.artem.university.dto.LectorDto;
import com.artem.university.mapper.LectorGlobalSearchMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectorService {

    private final LectorRepository lectorRepository;
    private final LectorGlobalSearchMapper lectorGlobalSearchMapper;

    public String globalSearchMessage(String name) {
        return globalSearch(name).stream()
            .map(lector -> lector.getFirstname() + " " + lector.getLastname())
            .collect(Collectors.joining(", "));
    }

    private List<LectorDto> globalSearch(String fragment) {
        return lectorRepository.findAllBy(fragment).stream()
            .map(lectorGlobalSearchMapper::map)
            .toList();
    }

}
