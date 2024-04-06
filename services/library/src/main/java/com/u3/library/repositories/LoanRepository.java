package com.u3.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.library.models.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  
}
