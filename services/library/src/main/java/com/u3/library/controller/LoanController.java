package com.u3.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.library.models.Loan;
import com.u3.library.services.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

  @GetMapping("/{id}")
  public Loan getLoanById(@PathVariable Long id) {
      return loanService.getLoanById(id);
  }

  @PostMapping
    public Loan createBook(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }
    
    @PutMapping("/{id}")
      public Loan updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
          return loanService.updateLoan(id, loanDetails);
      }
  
      @DeleteMapping("/{id}")
      public void delete(@PathVariable Long id) {
          loanService.deleteLoan(id);
      }

  }