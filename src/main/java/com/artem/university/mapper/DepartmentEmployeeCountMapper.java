package com.artem.university.mapper;

import com.artem.university.database.entity.Department;
import com.artem.university.dto.DepartmentEmployeeCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentEmployeeCountMapper implements Mapper<Department, DepartmentEmployeeCountDto> {

    @Override
    public DepartmentEmployeeCountDto map(Department object) {
        var employeeCount = object.getLectors().size();

        return new DepartmentEmployeeCountDto(object.getTitle(), employeeCount);
    }
}
