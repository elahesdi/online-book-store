package com.TOSAN.onlineBookStore.dto;

import com.TOSAN.onlineBookStore.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;

public class BookDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String title;

    private String author;

    public BookDto() {
    }

    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static BookDto convertToBookDto(Book book) {
        return new BookDto(book.getTitle(), book.getAuthor());
    }
}
