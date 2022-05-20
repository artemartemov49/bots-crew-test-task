package com.artem.university.database.repository;

import com.artem.university.database.entity.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectorRepository extends JpaRepository<Lector, Integer> {

    @Query("""
        SELECT l FROM Lector l
        WHERE UPPER(l.firstname) LIKE %:name% OR l.lastname LIKE %:name%
        """)
    List<Lector> findAllBy(String name);
}
