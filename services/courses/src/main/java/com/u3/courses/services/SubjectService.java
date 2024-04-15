package com.u3.courses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.u3.courses.models.Subject;
import com.u3.courses.repositories.SubjectRepository;

@Service
public class SubjectService {
    
    @Autowired
    SubjectRepository repository;


    public Optional<Subject> findById(Long id){
        return repository.findById(id);
    }
    
    public Optional<Subject> findByName(String name){
        return repository.findByName(name);
    }

    public List<Subject> findByWorkload(Integer workload){
        return repository.findByWorkload(workload);
    }

    public List<Subject> findAll(){
        return repository.findAll();
    }

    public ResponseEntity<String> save(Subject subject){
        repository.save(subject);
        return ResponseEntity.ok("Ok");
    }

    public ResponseEntity<String> update(Subject subject,Long id) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            repository.save(subject);
       }
        return ResponseEntity.ok("Ok");
    }

    public void deleteById(Long id){
            repository.deleteById(id);
    }


}
