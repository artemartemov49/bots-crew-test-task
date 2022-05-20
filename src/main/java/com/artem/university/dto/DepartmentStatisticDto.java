package com.artem.university.dto;

import com.artem.university.database.entity.Degree;
import java.util.Map;
import lombok.Value;

@Value
public class DepartmentStatisticDto {

    String name;

    Map<Degree, Long> degrees;
}
