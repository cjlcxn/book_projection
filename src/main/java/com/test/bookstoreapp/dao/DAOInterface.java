package com.test.bookstoreapp.dao;

import com.test.bookstoreapp.projections.BookNoGenre;
import com.test.bookstoreapp.projections.BookNoGenrePrice;
import com.test.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DAOInterface extends JpaRepository<Book, Integer> {
    // plain DataJPA method query
    List<Book> findByTitleAndPublisher(String title, String publisher);

    // DataJPA method query + custom DTO interface projection
//    List<BookNoGenre> findByTitle(String title);

    // DataJPA method query + dynamic interface projection
    <T> List<T> findByTitle(String title, Class<T> classType);

    // DataJPA @Query + dynamic interface projection
    @Query("SELECT new com.test.bookstoreapp.dao.projections.BookNoGenrePrice(book.id, book.title, book.publisher) FROM Book book WHERE book.id= ?1")
    BookNoGenrePrice findByIdNoGenrePrice(int id);






}
