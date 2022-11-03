package com.test.bookstoreapp.rest;

import com.test.bookstoreapp.dao.DAOInterface;
import com.test.bookstoreapp.entity.Book;
import com.test.bookstoreapp.exception.BookInvalidIdExc;
import com.test.bookstoreapp.rest.hateaosEntity.BookModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// main controller for "/book"
@RestController
@RequestMapping("/api")
public class BookController {

    private final DAOInterface DAOMethods;
    private final BookModelAssembler assembler;

    // DI for DAO to be used within controller
    // DI for entityModelAssembler
    @Autowired
    public BookController(DAOInterface DAOMethods, BookModelAssembler assembler) {
        this.DAOMethods = DAOMethods;
        this.assembler = assembler;
    }

    @PostMapping("/book")
    // @RequestBody parses the incoming request body (here is JSON) and saves it as a POJO (help from Jackson)
    public Book createBook(@Valid @RequestBody Book book) {
        DAOMethods.save(book);
        return book;
    }

    @GetMapping("/book")
    public CollectionModel<EntityModel<Book>> getBooks () {
        List<Book> books = DAOMethods.findAll();

        // creating an ArrayList of EntityModels, with each Book transformed with assembler
        List<EntityModel<Book>> booksWithRef = new ArrayList<>();
                for(Book book:books) {
                    booksWithRef.add(assembler.toModel(book));
                }

        return CollectionModel.of(booksWithRef,
                linkTo(methodOn(BookController.class).getBooks()).withSelfRel());
    }
//
    @GetMapping("/book/{bookId}")
    public EntityModel<Book> getBookById(@PathVariable int bookId) {
        try {
            Optional<Book> book = DAOMethods.findById(bookId);
            Book bookRetrieved = book.get();

            // throw custom error, if no query match
                return assembler.toModel(bookRetrieved);


        } catch (NoSuchElementException e) {
            System.out.println("Error is here:" + e.getMessage());
            throw new BookInvalidIdExc("Id not found - " + bookId);
        }
    }
//
    @PutMapping("/book")
    public EntityModel<Book> updateBook(@Valid @RequestBody Book book) {
        // handle error for no ID inputs, as it will trigger save instead of update
        if(book.getId() == 0) throw new BookInvalidIdExc("Id field should not be 0 or empty");

        DAOMethods.save(book);
        return assembler.toModel(book);
    }
//
    @DeleteMapping("/book/{bookId}")
    public String deleteBook(@PathVariable int bookId) {

        try {
            DAOMethods.deleteById(bookId);
        }catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        // throw custom error, if no query match
//        if (book == null) {
//            throw new BookInvalidIdExc("Id not found - " + bookId);
//        } else {
//            DAOMethods.deleteBook(bookId);
//
//        }
        return "Book deleted - " + bookId;
    }
}
