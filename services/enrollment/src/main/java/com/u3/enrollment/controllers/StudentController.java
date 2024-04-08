package com.u3.enrollment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.enrollment.models.Student;
import com.u3.enrollment.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
	public List<Student> findAll() {
        return studentService.findAll();
	}
    
    @GetMapping("findRm/{rm}")
    public Optional<Student> findByRm(@PathVariable String rm){
        return studentService.findByRm(rm);
    }

    @GetMapping("findName/{name}")
    public List<Student> findByName(@PathVariable String name){
        return studentService.findByName(name);
    }
    
    @GetMapping("findUsername/{username}")
    public List<Student> findByUsername(@PathVariable String username){
        return studentService.findByUsername(username);
    }

    @GetMapping("findCpf/{cpf}")
    public Optional<Student> findByCpf(@PathVariable String cpf){
        return studentService.findByCpf(cpf);
    }

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Student student){
        return studentService.save(student);
    }

    @DeleteMapping("delete/{rm}")
    public void delete(@PathVariable("rm") String rm){
        studentService.delete(rm);
    }





}
