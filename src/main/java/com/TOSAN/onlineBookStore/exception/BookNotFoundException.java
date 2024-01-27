package com.TOSAN.onlineBookStore.exception;

public class BookNotFoundException extends CustomErrorException{

    public BookNotFoundException(String title){
        super("Book not found: " + title);
    }
}
