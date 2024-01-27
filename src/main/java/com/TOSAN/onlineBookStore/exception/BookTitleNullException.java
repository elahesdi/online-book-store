package com.TOSAN.onlineBookStore.exception;


import java.util.function.Supplier;

public class BookTitleNullException extends CustomErrorException  {

    public BookTitleNullException(){
        super("Book title can not be Null!");
    }
}
