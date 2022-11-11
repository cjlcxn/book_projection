package com.test.bookstoreapp.services;

import com.test.bookstoreapp.dao.CustomDao;
import com.test.bookstoreapp.dao.DAOInterface;
import com.test.bookstoreapp.projections.BookNoGenre;
import com.test.bookstoreapp.projections.BookNoGenrePrice;
import com.test.bookstoreapp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    private DAOInterface bookDAO;
    private CustomDao customDao;

    @Autowired
    public BookServiceImpl(DAOInterface bookDAO, CustomDao customDao) {
        this.bookDAO = bookDAO;
        this.customDao = customDao;
    }

    @Override
    public void saveBook(Book book) {
        this.bookDAO.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = this.bookDAO.findAll();
//        Author author1 = books.get(0).getGenre().getAuthor();
//
//        books.get(0).setTitle("change for the better");
//        Genre genre1 = new Genre("random", author1);
//        books.get(0).setGenre(genre1);
//        this.bookDAO.save(books.get(0));
        System.out.println("xxxxxxxxxxxxxxxx");
        System.out.println("AFTER QUERY");
        return books;
    }

    @Override
    public Book findBookById(int bookId) throws RuntimeException{
        Optional<Book> book = this.bookDAO.findById(bookId);
        Book returningBook = null;
        if(book.isPresent()) {
            returningBook = book.get();
        }

        return returningBook;
    }

    @Override
    public void deleteBookById(int bookId) {
        this.bookDAO.deleteById(bookId);
    }

    @Override
    public Book findByTitleAndPublisher() {
        List<Book> books = bookDAO.findByTitleAndPublisher("test book!", "Mchel");
        return books.get(0);
    }

    @Override
    public BookNoGenre findByTitleNoGenre() {
        List<BookNoGenre> books = bookDAO.findByTitle("test book!", BookNoGenre.class);
        return books.get(0);
    }

    @Override
    public BookNoGenrePrice findByIdNoGenrePrice() {
        BookNoGenrePrice book= bookDAO.findByIdNoGenrePrice(1);
        return book;
    }

    @Override
    public BigDecimal findBookPriceWherePublisherIs5Letters() {
        BigDecimal price = customDao.findBookPriceWherePublisherIs5Letters();
        return price;
    }
}
