package com.artem.university.mapper;

import com.artem.university.database.entity.Degree;
import com.artem.university.database.entity.Department;
import com.artem.university.database.entity.Lector;
import com.artem.university.dto.DepartmentStatisticDto;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentStatisticMapper implements Mapper<Department, DepartmentStatisticDto> {

    @Override
    public DepartmentStatisticDto map(Department object) {
        var degrees = getDegrees(object);

        return new DepartmentStatisticDto(object.getTitle(), degrees);
    }

    private Map<Degree, Long> getDegrees(Department object) {
        return object.getLectors().stream()
            .map(Lector::getDegree)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
