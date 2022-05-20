package com.artem.university.database.repository;

import com.artem.university.database.entity.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Integer> {

    List<Lector> findAllByNameContainingIgnoreCase(String fragment);
}
