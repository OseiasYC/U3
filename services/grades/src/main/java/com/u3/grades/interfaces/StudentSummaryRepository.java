package com.u3.grades.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.grades.models.StudentSummary;

@Repository
public interface StudentSummaryRepository extends JpaRepository<StudentSummary, Long> {

    StudentSummary findByStudentRm(String studentRm);

}
