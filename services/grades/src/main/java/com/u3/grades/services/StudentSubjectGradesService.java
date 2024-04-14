package com.u3.grades.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.grades.interfaces.StudentSubjectGradesRepository;
import com.u3.grades.models.StudentSubjectGrades;
import com.u3.grades.models.StudentSummary;

@Service
public class StudentSubjectGradesService {
    
    @Autowired
    StudentSubjectGradesRepository studentSubjectGradesRepository;

    public void createStudentSubjectGrades(StudentSubjectGrades studentSubjectGrades) {
        studentSubjectGradesRepository.save(studentSubjectGrades);
    }

    public void updateStudentSubjectGrades(StudentSubjectGrades studentSubjectGrades) {
        studentSubjectGradesRepository.save(studentSubjectGrades);
    }

    public void deleteStudentSubjectGrades(Long studentSubjectGradesId) {
        studentSubjectGradesRepository.deleteById(studentSubjectGradesId);
    }

    public StudentSubjectGrades getStudentSubjectGrades(Long studentSubjectGradesId) {
        return studentSubjectGradesRepository.findById(studentSubjectGradesId).orElseThrow(() -> new RuntimeException("StudentSubjectGrades not found with ID: " + studentSubjectGradesId));
    }

    public List<StudentSubjectGrades> getAllStudentSubjectGrades() {
        return studentSubjectGradesRepository.findAll();
    }

    public List<StudentSubjectGrades> getStudentSubjectGradesByStudentSummary(StudentSummary studentSummary) {
        return studentSubjectGradesRepository.findByStudentSummary(studentSummary);
    }

    public StudentSubjectGrades getStudentSubjectGradesByCourseId(String courseId) {
        return studentSubjectGradesRepository.findByCourseId(courseId);
    }
    
}
