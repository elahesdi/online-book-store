package com.TOSAN.onlineBookStore.dto;

public class PurchaseDto {

    private String bookTitle;
    private int quantity;

    public PurchaseDto() {
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
