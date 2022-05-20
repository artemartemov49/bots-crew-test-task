package com.artem.university.database.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(exclude = "departments")
@ToString(exclude = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Lector implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer salary;

    @Builder.Default
    @ManyToMany(mappedBy = "lectors")
    List<Department> departments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    Degree degree;
}
