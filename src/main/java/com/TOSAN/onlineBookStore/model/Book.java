package com.TOSAN.onlineBookStore.model;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "book_id")
    private Long id;


    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @OneToOne(mappedBy="book", cascade = CascadeType.ALL)
    private Inventory inventory;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Order> orders;

    public Book(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
    }
    public Book(BookDto bookDto, Inventory inventory) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.inventory = inventory;
    }

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


//    public Set<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(Set<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }


    public Set<Order> getOrders() {
        return orders;
    }

//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
        for (Order order : orders) {
            order.getBooks().add(this);
        }
    }

}
