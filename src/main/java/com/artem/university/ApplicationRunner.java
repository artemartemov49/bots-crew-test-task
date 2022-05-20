package com.artem.university;

import com.artem.university.dto.CommandDto;
import com.artem.university.dto.TypeDto;
import com.artem.university.exception.InappropriateValueException;
import com.artem.university.service.DepartmentService;
import com.artem.university.service.LectorService;
import com.artem.university.util.ParserUtil;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {

                CommandDto command = getCommand(scanner);
                var value = command.getValue();
                var commandType = command.getType();

                String result = processCommand(value, commandType);
                log.info(result);

            } catch (InappropriateValueException e) {
                log.warn(ParserUtil.ERROR_MESSAGE);
            }
        }
    }

    private String processCommand(String value, TypeDto commandType) {
        var result = "";

        switch (commandType) {
            case HEAD_OF_DEPARTMENT -> result = departmentService.getHeadOfDepartmentMessage(value);
            case STATISTIC -> result = departmentService.getStatisticMessage(value);
            case AVERAGE_SALARY -> result = departmentService.getAverageSalaryMessage(value);
            case EMPLOYEE_COUNT -> result = departmentService.getEmployeeCountMessage(value);
            case GLOBAL_SEARCH -> result = lectorService.globalSearchMessage(value);
            default -> throw new InappropriateValueException(ParserUtil.ERROR_MESSAGE);
        }

        return result;
    }

    private CommandDto getCommand(Scanner scanner) {
        var input = scanner.nextLine();
        return ParserUtil.getCommand(input);
    }
}
