package com.u3.models.library;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class Loan {
    
    public Loan(String studentRm, Long bookId, String loanStatus, LocalDateTime loanDate, LocalDateTime returnDate) {
        this.studentRm = studentRm;
        this.bookId = bookId;
        this.loanStatus = loanStatus;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    private String studentRm;

    private Long bookId;

    private String loanStatus;

    @CreationTimestamp
    private LocalDateTime loanDate;

    private LocalDateTime returnDate;
}
