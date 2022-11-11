package com.test.bookstoreapp.entity;



import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "publisher")
    @NotNull
    @NotBlank(message = "Publisher is required")
    private String publisher;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "FK_genre"))
    private Genre genre;

    // no arg constructor required, for Hibernate to create a bean based on this class
    public Book() {
    }

    public Book(String title, Genre genre, String publisher, BigDecimal price) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
                ", price=" + price +
                '}';
    }
}
