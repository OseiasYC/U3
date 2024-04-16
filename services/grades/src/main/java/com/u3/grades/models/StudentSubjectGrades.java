package com.u3.grades.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.u3.grades.enums.Situation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class StudentSubjectGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseId;

    @Enumerated(EnumType.STRING)
    private Situation situation;

    private Integer totalSubjectWorkload;

    private double grades[] = new double[2];

    private Integer abscences;

    @ManyToOne
    @JsonBackReference
    private StudentSummary studentSummary;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date subjectEntryDate;
    
}
