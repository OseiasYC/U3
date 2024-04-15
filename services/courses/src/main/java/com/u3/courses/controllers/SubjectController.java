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

import com.u3.courses.models.Subject;
import com.u3.courses.services.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    
    @Autowired
    SubjectService service;

    @GetMapping("id/{id}")
    public Optional<Subject> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("name/{name}")
    public Optional<Subject> findByName(@PathVariable String name){
        return service.findByName(name);
    }

    @GetMapping("workload/{workload}")
    public List<Subject> findByWorkload(@PathVariable Integer workload){
        return service.findByWorkload(workload);
    }

    @GetMapping("/all")
    public List<Subject> findAll(){
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Subject subject){
        return service.save(subject);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@RequestBody Subject subject, @PathVariable Long id) {
        return service.update(subject, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id){
            service.deleteById(id);
    }
}
