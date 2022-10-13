package com.test.bookstoreapp.rest;

import com.test.bookstoreapp.dao.DAOInterface;
import com.test.bookstoreapp.entity.Book;
import com.test.bookstoreapp.exception.BookInvalidIdExc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

// main controller for "/book"
@RestController
@RequestMapping("/api")
public class BookController {

    private DAOInterface DAOMethods;

    // DI for DAO to be used within controller
    @Autowired
    public BookController(DAOInterface DAOMethods) {
        this.DAOMethods = DAOMethods;
    }

    @PostMapping("/book")
    // @RequestBody parses the incoming request body (here is JSON) and saves it as a POJO (help from Jackson)
    public Book createBook(@Valid @RequestBody Book book) {
        // id set to 0, so Hibernate will save instead of update later in session.saveOrUpdate
        book.setId(0);

        DAOMethods.saveOrUpdateBook(book);
        return book;
    }

    @GetMapping("/book")
    public List<Book> getBooks () {
        return DAOMethods.getAllBooks();
    }

    @GetMapping("/book/{bookId}")
    public Book findBookById(@PathVariable int bookId) {
        Book book = DAOMethods.findBookById(bookId);

        // throw custom error, if no query match
        if (book == null) {
            throw new BookInvalidIdExc("Id not found - " + bookId);
        } else {
            return book;
        }
    }

    @PutMapping("/book")
    public Book updateBook(@Valid @RequestBody Book book) {
        DAOMethods.saveOrUpdateBook(book);
        return book;
    }

    @DeleteMapping("/book/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        Book book = DAOMethods.findBookById(bookId);

        // throw custom error, if no query match
        if (book == null) {
            throw new BookInvalidIdExc("Id not found - " + bookId);
        } else {
            DAOMethods.deleteBook(bookId);
            return "Book deleted - " + bookId;
        }
    }
}
