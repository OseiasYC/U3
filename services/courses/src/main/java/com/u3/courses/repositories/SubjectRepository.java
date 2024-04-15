package com.u3.courses.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.u3.courses.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
  
    @Query(value = "SELECT * FROM subject WHERE id = ?1", nativeQuery = true)
    Optional<Subject> findById(Long id);

    @Query(value = "SELECT * FROM subject WHERE name = ?1", nativeQuery = true)
    Optional<Subject> findByName(String name);
    
    @Query(value = "SELECT * FROM subject WHERE workload = ?1", nativeQuery = true)
    List<Subject> findByWorkload(Integer workload);

    @Query(value = "SELECT * FROM subject ORDER BY id ASC", nativeQuery = true)
    List<Subject> findAll();

}
