package com.u3.models.library;

import lombok.Data;

@Data
public class Book {

    public Book(String title, String author, int amount) {
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    private String title;

    private String author;

    private Integer amount;

}
