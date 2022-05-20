package com.artem.university.mapper;

import com.artem.university.database.entity.Department;
import com.artem.university.database.entity.Lector;
import com.artem.university.dto.DepartmentAvgSalaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentAverageSalaryMapper implements Mapper<Department, DepartmentAvgSalaryDto> {

    @Override
    public DepartmentAvgSalaryDto map(Department object) {
        var avgSalary = getAvgSalary(object);

        return new DepartmentAvgSalaryDto(object.getName(), avgSalary);
    }

    private double getAvgSalary(Department object) {
        return object.getLectors().stream()
            .mapToInt(Lector::getSalary)
            .average()
            .orElse(0);
    }
}
