package com.artem.university.mapper;

import com.artem.university.database.entity.Lector;
import com.artem.university.dto.LectorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectorGlobalSearchMapper implements Mapper<Lector, LectorDto> {

    @Override
    public LectorDto map(Lector object) {
        return new LectorDto(object.getName());
    }
}
