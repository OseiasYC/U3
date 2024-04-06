package com.u3.library.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.library.models.Book;
import com.u3.library.models.Loan;
import com.u3.library.repositories.LoanRepository;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookService bookService;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found" + id));
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan loanDetails) {
      Loan loan = getLoanById(id);
      loan.setStudentRm(loanDetails.getStudentRm());
      loan.setBookId(loanDetails.getBookId());
      loan.setLoanStatus(loanDetails.getLoanStatus());
      loan.setLoanDate(loanDetails.getLoanDate());
      loan.setReturnDate(loanDetails.getReturnDate());
      return loanRepository.save(loan);
  }
      public Loan returnLoan(Long id) {
      Loan loan = getLoanById(id);
      return loanRepository.save(loan);
    } 

      public void deleteLoan(Long id) {
        Loan loan = getLoanById(id);
        loanRepository.delete(loan);
    }

}
