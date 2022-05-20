package com.artem.university.database.repository;

import com.artem.university.database.entity.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(String name);
}
