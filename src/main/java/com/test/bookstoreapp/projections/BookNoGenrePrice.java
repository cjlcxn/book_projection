package com.test.bookstoreapp.projections;

public class BookNoGenrePrice {
    private String title;
    private String publisher;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookNoGenrePrice(int id, String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookNoGenrePrice{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", id=" + id +
                '}';
    }
}
