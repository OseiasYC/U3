package com.u3.courses.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.u3.courses.models.Course;

import jakarta.transaction.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

  
    @Query(value = "SELECT * FROM course WHERE id = ?1", nativeQuery = true)
    Optional<Course> findById(String id);
    
    @Query(value = "SELECT * FROM course WHERE name = ?1", nativeQuery = true)
    Optional<Course> findByName(String name);
    
    @Query(value = "SELECT students_rm FROM course WHERE id = ?1", nativeQuery = true)
    List<String> findAllStudentsRmById(String id);

    @Query(value = "SELECT * FROM course ORDER BY name ASC", nativeQuery = true)
    List<Course> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course WHERE id = ?1", nativeQuery = true)
    void deleteById(String id);



} 
