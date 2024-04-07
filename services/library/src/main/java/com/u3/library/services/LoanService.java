package com.u3.library.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.library.enums.LoanStatus;
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
        Book book = bookService.getBookById(loan.getBookId());
            List<Loan> activeLoans = loanRepository.findByStudentRmAndBookIdAndLoanStatusIn(loan.getStudentRm(), 
            loan.getBookId(), Arrays.asList(LoanStatus.OVERDUE, LoanStatus.LOST, LoanStatus.BORROWED));
            
        if (!activeLoans.isEmpty()) {
            throw new RuntimeException("Student already has an active loan for this book");
        }
    
        if (book.getAmount() > 0) {
            book.setAmount(book.getAmount() - 1);
            bookService.updateBook(book.getId(), book);
        } else {
            throw new RuntimeException("Book is not available for loan");
        }
    
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan updateLoan) {
        Book book = bookService.getBookById(updateLoan.getBookId());
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found" + id ));

        if (updateLoan.getLoanStatus() == LoanStatus.RETURNED) {
                book.setAmount(book.getAmount() + 1); 
                bookService.updateBook(book.getId(), book); 
                loanRepository.delete(loan); 
                return null; 
        }
        
        bookService.updateBook(book.getId(), book); 
        loan.setStudentRm(updateLoan.getStudentRm());
        loan.setBookId(updateLoan.getBookId());
        loan.setLoanStatus(updateLoan.getLoanStatus());
        loan.setLoanDate(updateLoan.getLoanDate());
        loan.setReturnDate(updateLoan.getReturnDate());
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

