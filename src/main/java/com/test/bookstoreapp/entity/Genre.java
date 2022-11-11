package com.test.bookstoreapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "FK_author"))
    private Author author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "popularity_id", foreignKey = @ForeignKey(name = "FK_popularity"))
    private Popularity popularity;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre() {
    }

    public Genre(String name, Author author, Popularity popularity) {
        this.name = name;
        this.author = author;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}
