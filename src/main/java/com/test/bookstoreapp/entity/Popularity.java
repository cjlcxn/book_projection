package com.test.bookstoreapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="popularity")
public class Popularity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "review")
    @NotNull
    private String review;

    public Popularity() {
    }

    public Popularity(String review) {
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Popularity{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }
}
