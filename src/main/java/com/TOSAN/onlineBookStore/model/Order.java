package com.TOSAN.onlineBookStore.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = true, referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public Order(User user, Set<Book> books) {
        this.user = user;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Set<Book> getBooks() {
        return books;
    }

//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }
    public void setBooks(Set<Book> books) {
        this.books = books;
        for (Book book : books) {
            book.getOrders().add(this);
        }
    }

}
