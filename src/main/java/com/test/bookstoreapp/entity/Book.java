package com.test.bookstoreapp.entity;



import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// Book entity, used for ORM with MySQL
@Entity
@Table(name="book")
public class Book {

    // setting up fields that reflect the MySQL table - book
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "genre")
    @NotNull
    private String genre;

    @Column(name = "publisher")
    @NotNull
    private String publisher;

    @Column(name = "author")
    @NotNull
    private String author;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    // no arg constructor required, for Hibernate to create a bean based on this class
    public Book() {
    }

    public Book(String title, String genre, String publisher, String author, BigDecimal price) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
