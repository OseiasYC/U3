package com.u3.library.services;

import com.u3.library.models.Book;
import com.u3.library.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found" + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public Book borrowBook(Long id) {
      Book book = getBookById(id);
      if (book.getQuantity() > 0) {
          book.setQuantity(book.getQuantity() - 1);
          return bookRepository.save(book);
      } else {
          throw new RuntimeException("Book is not available for borrowing");
      }
  }

  public Book returnBook(Long id) {
      Book book = getBookById(id);
      book.setQuantity(book.getQuantity() + 1);
      return bookRepository.save(book);
  }


    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setQuantity(bookDetails.getQuantity());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
  }
  
