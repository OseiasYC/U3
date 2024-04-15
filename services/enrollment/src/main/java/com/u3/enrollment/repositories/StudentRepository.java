package com.u3.enrollment.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.u3.enrollment.models.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

    @Query(value = "SELECT * FROM student WHERE rm = ?1", nativeQuery = true)
    Optional<Student> findByRm(String rm);
    
    @Query(value = "SELECT * FROM student WHERE name = ?1", nativeQuery = true)
    List<Student> findByName(String name);
    
    @Query(value = "SELECT * FROM student WHERE username = ?1", nativeQuery = true)
    List<Student> findByUsername(String username);
    
    @Query(value = "SELECT * FROM student WHERE cpf = ?1", nativeQuery = true)
    Optional<Student> findByCpf(String cpf);
    
    @Query(value = "SELECT * FROM student ORDER BY rm ASC", nativeQuery = true)
    List<Student> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student WHERE rm = ?1", nativeQuery = true)
    void deleteByRm(String rm);
    

}
