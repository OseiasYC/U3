package com.u3.courses.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.courses.models.Course;
import com.u3.courses.services.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/id/{id}")
    public Optional<Course> findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<Course> findByName(@PathVariable String name){
        return service.findByName(name);
    }

    @GetMapping("/students/{id}")
    public List<String> findAllStudentsRmById(@PathVariable String id){
        return service.findAllStudentsRmById(id);
    }

    @GetMapping("/all")
    public List<Course> findAll(){
        return service.findAll();
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Course course){
        return service.save(course);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Course course, @PathVariable String id) {
        return service.update(course, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
            service.deleteById(id);
    }
}
