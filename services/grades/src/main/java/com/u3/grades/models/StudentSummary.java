package com.u3.grades.models;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.u3.grades.enums.Shift;
import com.u3.grades.enums.Situation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class StudentSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentRm;

    private String courseId;

    private double totalCourseWorkloadPercentage;

    private double globalAverage;

    @Enumerated(EnumType.STRING)
    private Situation situation;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @OneToMany(mappedBy = "studentSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentSubjectGrades> studentSubjectGrades; 

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date courseEntryDate;

}