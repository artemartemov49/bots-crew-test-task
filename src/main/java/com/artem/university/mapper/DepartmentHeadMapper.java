package com.artem.university.mapper;

import com.artem.university.database.entity.Department;
import com.artem.university.dto.DepartmentHeadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentHeadMapper implements Mapper<Department, DepartmentHeadDto> {

    @Override
    public DepartmentHeadDto map(Department object) {
        var headDepartment = object.getHeadDepartment();

        return new DepartmentHeadDto(object.getName(), headDepartment.getFirstname(), headDepartment.getLastname());
    }
}
