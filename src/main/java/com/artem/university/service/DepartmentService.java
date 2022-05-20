package com.artem.university.service;

import static com.artem.university.util.ConsoleUtil.AVERAGE_SALARY;
import static com.artem.university.util.ConsoleUtil.EMPLOYEE_COUNT;
import static com.artem.university.util.ConsoleUtil.HEAD_OF_DEPARTMENT;
import static com.artem.university.util.ConsoleUtil.NOT_FOUND;
import static com.artem.university.util.ConsoleUtil.STATISTIC;

import com.artem.university.database.entity.Degree;
import com.artem.university.database.repository.DepartmentRepository;
import com.artem.university.dto.DepartmentAvgSalaryDto;
import com.artem.university.dto.DepartmentEmployeeCountDto;
import com.artem.university.dto.DepartmentHeadDto;
import com.artem.university.dto.DepartmentStatisticDto;
import com.artem.university.mapper.DepartmentAverageSalaryMapper;
import com.artem.university.mapper.DepartmentEmployeeCountMapper;
import com.artem.university.mapper.DepartmentHeadMapper;
import com.artem.university.mapper.DepartmentStatisticMapper;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentEmployeeCountMapper departmentEmployeeCountMapper;
    private final DepartmentAverageSalaryMapper departmentAverageSalaryMapper;
    private final DepartmentStatisticMapper departmentStatisticMapper;
    private final DepartmentHeadMapper departmentHeadMapper;

    public String getHeadOfDepartmentMessage(String name) {
        var department = getHeadOfDepartment(name)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return HEAD_OF_DEPARTMENT.formatted(department.getDepartmentName(), department.getFirstname(), department.getLastname());
    }

    public String getStatisticMessage(String name) {
        var statistic = getStatistic(name)
            .map(DepartmentStatisticDto::getDegrees)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return STATISTIC.formatted(getDegree(statistic, Degree.ASSISTANT),
            getDegree(statistic, Degree.ASSOCIATE_PROFESSOR),
            getDegree(statistic, Degree.PROFESSOR));
    }

    public String getAverageSalaryMessage(String name) {
        var avgSalary = getAverageSalary(name)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return AVERAGE_SALARY.formatted(avgSalary.getName(), avgSalary.getAvgSalary());
    }

    public String getEmployeeCountMessage(String name) {
        var employeeCount = getEmployeeCount(name)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return EMPLOYEE_COUNT.formatted(employeeCount.getEmployeeCount());
    }

    private Optional<DepartmentHeadDto> getHeadOfDepartment(String name) {
        return departmentRepository.findByName(name)
            .map(departmentHeadMapper::map);
    }

    private Optional<DepartmentStatisticDto> getStatistic(String name) {
        return departmentRepository.findByName(name)
            .map(departmentStatisticMapper::map);
    }

    private Optional<DepartmentAvgSalaryDto> getAverageSalary(String name) {
        return departmentRepository.findByName(name)
            .map(departmentAverageSalaryMapper::map);
    }

    private Optional<DepartmentEmployeeCountDto> getEmployeeCount(String name) {
        return departmentRepository.findByName(name)
            .map(departmentEmployeeCountMapper::map);
    }

    private Long getDegree(Map<Degree, Long> statistic, Degree degree) {
        return statistic.getOrDefault(degree, 0L);
    }

}
