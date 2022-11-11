package com.test.bookstoreapp.services;

import com.test.bookstoreapp.projections.BookNoGenre;
import com.test.bookstoreapp.projections.BookNoGenrePrice;
import com.test.bookstoreapp.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void saveBook(Book book);
    List<Book> findAllBooks();
    Book findBookById(int bookId);
    void deleteBookById(int bookId);

    // custom queries
    // standard HQL, w/ DataJPA instead of custom queries
    Book findByTitleAndPublisher ();

    // DTO interface projection, w/ DataJPA
    BookNoGenre findByTitleNoGenre ();

    // DTO class projection, w/ @Query
    BookNoGenrePrice findByIdNoGenrePrice();

    //custom impl of custom DAO interface, w/ EntityManager & sessions
    BigDecimal findBookPriceWherePublisherIs5Letters();

}
