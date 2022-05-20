package com.artem.university.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleUtil {

    public static final String HEAD_OF_DEPARTMENT = "Head of {%s} department is {%s %s}";
    public static final String AVERAGE_SALARY = "The average salary of {%s} is {%s}.";
    public static final String EMPLOYEE_COUNT = "{%s}.";
    public static final String STATISTIC = System.lineSeparator() + """
        assistants - {%s}.
        associate professors - {%s}
        professors -{%s}.
        """;

    public static final String NOT_FOUND = "Information not found";
}
