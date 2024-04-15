package com.u3.courses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.u3.courses.models.Course;
import com.u3.courses.repositories.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    CourseRepository repository;
    
    public Optional<Course> findById(String id){
        return repository.findById(id);
    }

    public Optional<Course> findByName(String name){
        return repository.findByName(name);
    }
    
    public List<String> findAllStudentsRmById(String id){
        return repository.findAllStudentsRmById(id);
    }

    public List<Course> findAll(){
        return repository.findAll();
    }
    
    public ResponseEntity<String> save(Course course){
        repository.save(course);
        return ResponseEntity.ok("Ok");
    }

    public ResponseEntity<String> update(Course course,String id) {
        Optional<Course> optionalCourse = repository.findById(id);
        if (optionalCourse.isPresent()) {
            repository.save(course);
       }
        return ResponseEntity.ok("Ok");
    }

    public void deleteById(String id){
        Optional<Course> optionalCourse = repository.findById(id);
        if (optionalCourse.isPresent()) {
            repository.deleteById(id);
       }
    }
}
