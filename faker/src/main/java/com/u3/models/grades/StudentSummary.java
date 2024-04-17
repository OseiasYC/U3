package com.u3.models.grades;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class StudentSummary {

    public StudentSummary(String studentRm, String courseId, double totalCourseWorkloadPercentage, double globalAverage,
            String situation, String shift, List<StudentSubjectGrades> studentSubjectGrades, Date courseEntryDate) {
        this.studentRm = studentRm;
        this.courseId = courseId;
        this.totalCourseWorkloadPercentage = totalCourseWorkloadPercentage;
        this.globalAverage = globalAverage;
        this.situation = situation;
        this.shift = shift;
        this.studentSubjectGrades = studentSubjectGrades;
        this.courseEntryDate = courseEntryDate;
    }

    private String studentRm;

    private String courseId;

    private double totalCourseWorkloadPercentage;

    private double globalAverage;

    private String situation;

    private String shift;

    private List<StudentSubjectGrades> studentSubjectGrades;

    private Date courseEntryDate;

}
