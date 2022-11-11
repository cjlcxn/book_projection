package com.test.bookstoreapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    @NotNull
    private String fulName;

    public Author() {
    }

    public Author(String fulName) {
        this.fulName = fulName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "fulName='" + fulName + '\'' +
                '}';
    }
}
