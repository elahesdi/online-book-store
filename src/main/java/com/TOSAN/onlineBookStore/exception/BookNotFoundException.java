package com.TOSAN.onlineBookStore.exception;

public class BookNotFoundException extends EntityNotFound{

    public BookNotFoundException(String title){
        super("Book not found: " + title);
    }
}
