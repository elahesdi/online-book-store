package com.TOSAN.onlineBookStore.dto;

import com.TOSAN.onlineBookStore.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

public class BookDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String title;

    private String author;

    private int inventory;

    public BookDto() {
    }

    public BookDto(String title, String author, int inventory) {
        this.title = title;
        this.author = author;
        this.inventory = inventory;
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
        return new BookDto(book.getTitle(), book.getAuthor(), book.getInventory().getInventory());
    }


    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
