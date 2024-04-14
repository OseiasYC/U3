package com.u3.grades.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.grades.models.StudentSubjectGrades;
import com.u3.grades.models.StudentSummary;

@Repository
public interface StudentSubjectGradesRepository extends JpaRepository<StudentSubjectGrades, Long> {

    List<StudentSubjectGrades> findByStudentSummary(StudentSummary studentSummary);

    StudentSubjectGrades findByCourseId(String courseId);

}
