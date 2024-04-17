package com.u3.models.grades;

import java.util.Date;

import lombok.Data;

@Data
public class StudentSubjectGrades {

    public StudentSubjectGrades(String courseId, String situation, Integer totalSubjectWorkload, Double[] grades,
            Integer abscences, StudentSummary studentSummary, Date subjectEntryDate) {
        this.courseId = courseId;
        this.situation = situation;
        this.totalSubjectWorkload = totalSubjectWorkload;
        this.grades = grades;
        this.abscences = abscences;
        this.studentSummary = studentSummary;
        this.subjectEntryDate = subjectEntryDate;
    }

    private String courseId;

    private String situation;

    private Integer totalSubjectWorkload;

    private Double grades[] = new Double[2];

    private Integer abscences;

    private StudentSummary studentSummary;

    private Date subjectEntryDate;

}
