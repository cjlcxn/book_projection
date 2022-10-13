package com.test.bookstoreapp.dao;

import com.test.bookstoreapp.entity.Book;

import java.util.List;

public interface DAOInterface {
    public List<Book> getAllBooks ();

    public void saveOrUpdateBook (Book book);

    public Book findBookById (int id);

    public void deleteBook(int id);
}
