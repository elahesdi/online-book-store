package com.TOSAN.onlineBookStore.exception;



public class BookTitleNullException extends CustomErrorException  {

    public BookTitleNullException(){
        super("Book title can not be Null!");
    }
}
