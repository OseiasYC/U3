package com.u3.enrollment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.u3.enrollment.models.Student;
import com.u3.enrollment.repositories.StudentRepository;



@Service
public class StudentService {

    @Autowired
    StudentRepository repository;
    
    
    public Optional<Student> findByRm(String rm){
        return repository.findByRm(rm);
    }
    
    public List<Student> findByName(String name){
        return repository.findByName(name);
    }
    
    public List<Student> findByUsername(String username){
        return repository.findByUsername(username);
    }
    
    public Optional<Student> findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
    
    public ResponseEntity<String> save(Student student){
        repository.save(student);
        return ResponseEntity.ok("Ok");
    }
    
    public List<Student> findAll(){
        return repository.findAll();
    }
    
    public ResponseEntity<String> update(Student student,String rm) {
        Optional<Student> optionalStudent = repository.findById(rm);
        if (optionalStudent.isPresent()) {
            repository.save(student);
        }
        return ResponseEntity.ok("Ok");
    }

    public void delete(String rm){
         Optional<Student> optionalStudent = repository.findByRm(rm);
         if (optionalStudent.isPresent()) {
             repository.deleteByRm(rm);
        }
         
     }

}
