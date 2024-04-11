package com.u3.library.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.library.enums.LoanStatus;
import com.u3.library.models.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStudentRmAndBookIdAndLoanStatusIn(String studentRm, Long bookId, List<LoanStatus> loanStatuses);
   
  }
