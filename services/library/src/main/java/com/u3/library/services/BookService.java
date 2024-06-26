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

    public Book returnBook(Long id) {
        Book book = getBookById(id);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updateBook) {
        Book book = getBookById(id);
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setAmount(updateBook.getAmount());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
