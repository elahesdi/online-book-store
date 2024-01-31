package com.TOSAN.onlineBookStore.exception;

public class OutOfStockException extends CustomErrorException{
    public OutOfStockException(String item){
        super("The book is out of stock: " + item);
    }
}
