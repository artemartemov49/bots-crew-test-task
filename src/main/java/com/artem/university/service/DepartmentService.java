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

    public String getHeadOfDepartmentMessage(String title) {
        var department = getHeadOfDepartment(title)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return HEAD_OF_DEPARTMENT.formatted(
            department.getTitle(),
            department.getFirstname(),
            department.getLastname()
        );
    }

    public String getStatisticMessage(String title) {
        var statistic = getStatistic(title)
            .map(DepartmentStatisticDto::getDegrees)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return STATISTIC.formatted(
            getDegree(statistic, Degree.ASSISTANT),
            getDegree(statistic, Degree.ASSOCIATE_PROFESSOR),
            getDegree(statistic, Degree.PROFESSOR)
        );
    }

    public String getAverageSalaryMessage(String title) {
        var avgSalary = getAverageSalary(title)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return AVERAGE_SALARY.formatted(avgSalary.getTitle(), avgSalary.getAvgSalary());
    }

    public String getEmployeeCountMessage(String title) {
        var employeeCount = getEmployeeCount(title)
            .orElseThrow(() -> new NullPointerException(NOT_FOUND));

        return EMPLOYEE_COUNT.formatted(employeeCount.getEmployeeCount());
    }

    private Optional<DepartmentHeadDto> getHeadOfDepartment(String title) {
        return departmentRepository.findByTitle(title)
            .map(departmentHeadMapper::map);
    }

    private Optional<DepartmentStatisticDto> getStatistic(String title) {
        return departmentRepository.findByTitle(title)
            .map(departmentStatisticMapper::map);
    }

    private Optional<DepartmentAvgSalaryDto> getAverageSalary(String title) {
        return departmentRepository.findByTitle(title)
            .map(departmentAverageSalaryMapper::map);
    }

    private Optional<DepartmentEmployeeCountDto> getEmployeeCount(String title) {
        return departmentRepository.findByTitle(title)
            .map(departmentEmployeeCountMapper::map);
    }

    private Long getDegree(Map<Degree, Long> statistic, Degree degree) {
        return statistic.getOrDefault(degree, 0L);
    }

}
