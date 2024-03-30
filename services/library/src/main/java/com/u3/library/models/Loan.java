package com.u3.library.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.u3.library.enums.LoanStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentRm;

    private Long bookId;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @CreationTimestamp
    private LocalDateTime loanDate;

    private LocalDateTime returnDate;
}
