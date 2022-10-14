package com.test.bookstoreapp.dao;

import com.test.bookstoreapp.entity.Book;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

// create a DAO class, that communicates btwn Controller and SessionFactory / EntityManager
@Repository
public class DAOimplement implements DAOInterface{

    private EntityManager entityManager;

    // DI for entityManager / SessionFactory
    @Autowired
    public DAOimplement (EntityManager entityManagerFactory) {
        this.entityManager = entityManagerFactory;
    }

    // @transactional auto calls beginTransaction( ) and commit( ).
    @Override
    @Transactional
    public List<Book> getAllBooks() {

        // retieve current hibernate session
        Session session = entityManager.unwrap(Session.class);

        // query DB for all Book (object relection of Table)
        List<Book> books = session.createQuery("from Book").getResultList();
        return books;
    }

    @Override
    @Transactional
    public void saveOrUpdateBook(Book book) {
        // retieve current hibernate session
        Session session = entityManager.unwrap(Session.class);

        // if Book.id was set to 0 or null, then add Book to DB. Else, any +ve number will update the Book with respective id.
        session.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public Book findBookById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Book book = session.get(Book.class, id);
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        Session session = entityManager.unwrap(Session.class);
        // search book via id, and delete
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

}
