package com.u3.grades.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.grades.interfaces.StudentSummaryRepository;
import com.u3.grades.models.StudentSummary;

@Service
public class StudentSummaryService {
    
    @Autowired
    StudentSummaryRepository studentSummaryRepository;

    public void createStudentSummary(StudentSummary studentSummary) {
        studentSummaryRepository.save(studentSummary);
    }

    public void updateStudentSummary(StudentSummary studentSummary) {
        studentSummaryRepository.save(studentSummary);
    }

    public void deleteStudentSummary(Long studentId) {
        studentSummaryRepository.deleteById(studentId);
    }

    public StudentSummary getStudentSummary(Long studentSummaryId) {
        return studentSummaryRepository.findById(studentSummaryId).orElseThrow(() -> new RuntimeException("StudentSummary not found with ID: " + studentSummaryId));
    }

    public List<StudentSummary> getAllStudentSummaries() {
        return studentSummaryRepository.findAll();
    }

    public StudentSummary getStudentSummaryByStudentRm(String studentRm) {
        return studentSummaryRepository.findByStudentRm(studentRm);
    }
    
}
