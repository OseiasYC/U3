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
    StudentRepository studentRepository;
    
    
    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    
    public Optional<Student> findByRm(String rm){
        return studentRepository.findByRm(rm);
    }
    
    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
    
    public List<Student> findByUsername(String username){
        return studentRepository.findByUsername(username);
    }
    
    public Optional<Student> findByCpf(String cpf){
        return studentRepository.findByCpf(cpf);
    }
    
    public ResponseEntity<String> save(Student student){
         studentRepository.save(student);
         return ResponseEntity.ok("Ok");
     }
        
     public void delete(String rm){
         Optional<Student> optionalStudent = studentRepository.findByRm(rm);
         if (optionalStudent.isPresent()) {
             studentRepository.deleteByRm(rm);
        }
         
     }


    // public List<StudentDTO> createStudentDTO(Student student){}
    
    // public StudentDTO createStudentDTO(Student student){}
}
