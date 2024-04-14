package com.u3.grades.controllers;

import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.u3.grades.models.StudentSubjectGrades;
import com.u3.grades.models.StudentSummary;
import com.u3.grades.services.StudentSubjectGradesService;
import com.u3.grades.services.StudentSummaryService;

@RestController
@RequestMapping("/grades")
public class GradesController {
    
    @Autowired
    StudentSummaryService studentSummaryService;

    @Autowired
    StudentSubjectGradesService studentSubjectGradesService;

    @GetMapping("/studentsummary/{studentSummaryId}")
    public ResponseEntity<StudentSummary> getStudentSummary(@PathVariable Long studentSummaryId) {
        return ResponseEntity.ok(studentSummaryService.getStudentSummary(studentSummaryId));
    }

    @GetMapping("/studentsubjectgrades/{studentSubjectGradesId}")
    public ResponseEntity<StudentSubjectGrades> getStudentSubjectGrades(@PathVariable Long studentSubjectGradesId) {
        return ResponseEntity.ok(studentSubjectGradesService.getStudentSubjectGrades(studentSubjectGradesId));
    }

    @GetMapping("/studentsummary/all")
    public ResponseEntity<List<StudentSummary>> getAllStudentSummaries() {
        return ResponseEntity.ok(studentSummaryService.getAllStudentSummaries());
    }

    @GetMapping("/studentsubjectgrades/all")
    public ResponseEntity<List<StudentSubjectGrades>> getAllStudentSubjectGrades() {
        return ResponseEntity.ok(studentSubjectGradesService.getAllStudentSubjectGrades());
    }
    
    @GetMapping("/studentsummary/student/{studentRm}")
    public ResponseEntity<StudentSummary> getStudentSummaryByStudentRm(@PathVariable String studentRm) {
        return ResponseEntity.ok(studentSummaryService.getStudentSummaryByStudentRm(studentRm));
    }

    @GetMapping("/studentsubjectgrades/student/{studentSummaryId}")
    public ResponseEntity<List<StudentSubjectGrades>> getStudentSubjectGradesByStudentSummary(
            @PathVariable Long studentSummaryId) {
        return ResponseEntity.ok(studentSubjectGradesService.getStudentSubjectGradesByStudentSummary(studentSummaryService.getStudentSummary(studentSummaryId)));
    }
    
    @GetMapping("/studentsubjectgrades/course/{courseId}")
    public ResponseEntity<StudentSubjectGrades> getStudentSubjectGradesByCourseId(@PathVariable String courseId) {
        return ResponseEntity.ok(studentSubjectGradesService.getStudentSubjectGradesByCourseId(courseId));
    }

    @PostMapping("/studentsummary/create")
    public ResponseEntity<String> createStudentSummary(@RequestBody StudentSummary studentSummary,
            UriComponentsBuilder uriComponentsBuilder) {
        studentSummaryService.createStudentSummary(studentSummary);
        var uri = uriComponentsBuilder.path("/grades/studentsummary/{id}").buildAndExpand(studentSummary.getId())
                .toUri();
        return ResponseEntity.created(uri).body("StudentSummary created successfully");
    }
    
    @PostMapping("/studentsubjectgrades/create")
    public ResponseEntity<String> createStudentSubjectGrades(@RequestBody StudentSubjectGrades studentSubjectGrades,
            UriComponentsBuilder uriComponentsBuilder) {
        studentSubjectGradesService.createStudentSubjectGrades(studentSubjectGrades);
        var uri = uriComponentsBuilder.path("/grades/studentsubjectgrades/{id}")
                .buildAndExpand(studentSubjectGrades.getId())
                .toUri();
        return ResponseEntity.created(uri).body("StudentSubjectGrades created successfully");
    }
    
    @PutMapping("/studentsummary/update/{studentSummaryId}")
    public ResponseEntity<String> updateStudentSummary(@RequestBody StudentSummary studentSummary,
            @PathVariable Long studentSummaryId) {
        studentSummaryService.updateStudentSummary(studentSummary);
        return ResponseEntity.ok("StudentSummary updated successfully");
    }
    
    @PutMapping("/studentsubjectgrades/update/{studentSubjectGradesId}")
    public ResponseEntity<String> updateStudentSubjectGrades(@RequestBody StudentSubjectGrades studentSubjectGrades,
            @PathVariable Long studentSubjectGradesId) {
        studentSubjectGradesService.updateStudentSubjectGrades(studentSubjectGrades);
        return ResponseEntity.ok("StudentSubjectGrades updated successfully");
    }

    @DeleteMapping("/studentsummary/delete/{studentSummaryId}")
    public ResponseEntity<String> deleteStudentSummary(@PathVariable Long studentSummaryId) {
        studentSummaryService.deleteStudentSummary(studentSummaryId);
        return ResponseEntity.ok("StudentSummary deleted successfully");
    }

    @DeleteMapping("/studentsubjectgrades/delete/{studentSubjectGradesId}")
    public ResponseEntity<String> deleteStudentSubjectGrades(@PathVariable Long studentSubjectGradesId) {
        studentSubjectGradesService.deleteStudentSubjectGrades(studentSubjectGradesId);
        return ResponseEntity.ok("StudentSubjectGrades deleted successfully");
    }

}
