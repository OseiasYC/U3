package com.u3.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.u3.library.models.Book;
import com.u3.library.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    BookService bookService;
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updateBook) {
        return bookService.updateBook(id, updateBook);
    }

    @GetMapping("/findAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
