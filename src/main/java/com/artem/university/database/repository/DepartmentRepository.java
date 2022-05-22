package com.artem.university.database.repository;

import com.artem.university.database.entity.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @EntityGraph(attributePaths = "headDepartment")
    @Query("""
        SELECT d FROM Department d
        WHERE d.title = :title
                """)
    Optional<Department> headOfDepartment(String title);

    Optional<Department> findByTitle(String title);
}
