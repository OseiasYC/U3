package com.u3.library.repositories;

import com.u3.library.models.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE UPPER(title) LIKE UPPER(?1%);", nativeQuery = true)
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);
}