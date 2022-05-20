package com.artem.university.util;

import com.artem.university.dto.CommandDto;
import com.artem.university.dto.TypeDto;
import com.artem.university.exception.InappropriateValueException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParserUtil {

    private static final String DEPARTMENT_NAME = "departmentName";

    private static final Pattern HEAD_OF_DEPARTMENT =
        Pattern.compile("^\\s*(?:Who is head of department\\s*(?<" + DEPARTMENT_NAME + ">\\p{L}+))\\s*$");
    private static final Pattern STATISTIC =
        Pattern.compile("^\\s*(?:Show\\s*(?<" + DEPARTMENT_NAME + ">\\p{L}+)) statistics\\s*$");
    private static final Pattern AVERAGE_SALARY =
        Pattern.compile("^\\s*(?:Show the average salary for the department (?<" + DEPARTMENT_NAME + ">\\p{L}+))\\s*$");
    private static final Pattern EMPLOYEE_COUNT =
        Pattern.compile("^\\s*(?:Show count of employee for\\s*(?<" + DEPARTMENT_NAME + ">\\p{L}+))\\s*$");
    private static final Pattern GLOBAL_SEARCH =
        Pattern.compile("^\\s*(?:Global search by\\s*(?<" + DEPARTMENT_NAME + ">\\p{L}+))\\s*$");

    private static final Map<Pattern, TypeDto> PATTERNS = Map.of(
        HEAD_OF_DEPARTMENT, TypeDto.HEAD_OF_DEPARTMENT,
        STATISTIC, TypeDto.STATISTIC,
        AVERAGE_SALARY, TypeDto.AVERAGE_SALARY,
        EMPLOYEE_COUNT, TypeDto.EMPLOYEE_COUNT,
        GLOBAL_SEARCH, TypeDto.GLOBAL_SEARCH
    );

    public static final String ERROR_MESSAGE = "Inappropriate format";

    public static CommandDto getCommand(String input) {
        Matcher matcher = getMatcher(input);
        var pattern = getPattern(matcher);
        var value = matcher.group(DEPARTMENT_NAME);

        return new CommandDto(value, pattern);
    }

    private static Matcher getMatcher(String input) {
        return PATTERNS.keySet().stream()
            .map(pattern -> pattern.matcher(input))
            .filter(Matcher::find)
            .findFirst()
            .orElseThrow(() -> new InappropriateValueException(ERROR_MESSAGE));
    }

    private static TypeDto getPattern(Matcher matcher) {
        return PATTERNS.get(matcher.pattern());
    }

}
