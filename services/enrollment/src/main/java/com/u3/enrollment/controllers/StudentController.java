package com.u3.enrollment.controllers;

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

import com.u3.enrollment.models.Student;
import com.u3.enrollment.services.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService service;

    @GetMapping("rm/{rm}")
    public Optional<Student> findByRm(@PathVariable String rm){
        return service.findByRm(rm);
    }
    
    @GetMapping("name/{name}")
    public List<Student> findByName(@PathVariable String name){
        return service.findByName(name);
    }
    
    @GetMapping("username/{username}")
    public List<Student> findByUsername(@PathVariable String username){
        return service.findByUsername(username);
    }
    
    @GetMapping("cpf/{cpf}")
    public Optional<Student> findByCpf(@PathVariable String cpf){
        return service.findByCpf(cpf);
    }
    
    @GetMapping("/all")
    public List<Student> findAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Student student){
        return service.save(student);
    }
    
    @PutMapping("update/{rm}")
    public ResponseEntity<String> update(@RequestBody Student student, @PathVariable String rm) {
        return service.update(student, rm);
    }
    
    @DeleteMapping("delete/{rm}")
    public void delete(@PathVariable("rm") String rm){
        service.delete(rm);
    }





}
