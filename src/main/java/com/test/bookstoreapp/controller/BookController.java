package com.test.bookstoreapp.controller;

import com.test.bookstoreapp.dao.DAOInterface;
import com.test.bookstoreapp.projections.BookNoGenre;
import com.test.bookstoreapp.projections.BookNoGenrePrice;
import com.test.bookstoreapp.entity.Author;
import com.test.bookstoreapp.entity.Book;
import com.test.bookstoreapp.entity.Genre;
import com.test.bookstoreapp.entity.Popularity;
import com.test.bookstoreapp.exception.BookInvalidIdExc;
import com.test.bookstoreapp.controller.hateaosEntity.BookModelAssembler;
import com.test.bookstoreapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// main controller for "/book"
@RestController
@RequestMapping("/api")
public class BookController {

    private final DAOInterface DAOMethods;
    private final BookModelAssembler assembler;
    private final BookService bookService;

    // DI for DAO to be used within controller
    // DI for entityModelAssembler
    @Autowired
    public BookController(DAOInterface DAOMethods, BookModelAssembler assembler, BookService bookService) {
        this.DAOMethods = DAOMethods;
        this.assembler = assembler;
        this.bookService = bookService;
    }

    @PostMapping("/book")
    // @RequestBody parses the incoming request body (here is JSON) and saves it as a POJO (help from Jackson)
    public void createBook() {
        Author author1 = new Author("Benjamin Jumbeliah");

        Popularity popularity = new Popularity("meh");

        Genre genre1 = new Genre("Horror", author1, popularity);
        Genre genre2 = new Genre("Fantasy", author1, popularity);

        Book book1 = new Book("wow!", genre1, "Forbes", new BigDecimal("100.56"));
        Book book2 = new Book("test book!", genre2, "Mchel", new BigDecimal("55.78"));
        Book book3 = new Book("granola bars!", genre2, "Planks", new BigDecimal("49.99"));

        // using controller layer
        System.out.println("saving 1st book");
        this.DAOMethods.save(book1);
        System.out.println("saving 2nd book");
        this.DAOMethods.save(book2);
        System.out.println("saving 3rd book");
        this.DAOMethods.save(book3);

        // using service layer
//        this.bookService.saveBook(book1);
//        this.bookService.saveBook(book2);
//        this.bookService.saveBook(book3);

           }

    @GetMapping("/book")
    public CollectionModel<EntityModel<Book>> getBooks () {
        // controller query for books
        System.out.println("Starting service");
        List<Book> books = this.DAOMethods.findAll();
        List<Book> books1= this.DAOMethods.findAll();
        List<Book> books2 = this.DAOMethods.findAll();
        System.out.println(books);
        System.out.println("BEFORE: " + books.get(0));
        Book book1 = books.get(0);
        book1.setTitle("wowzers!");
        this.DAOMethods.save(book1);

        System.out.println("AFTER: " + books.get(0));

        //service query
//        System.out.println("Starting service");
//        List<Book> books = this.bookService.findAllBooks();
//
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
            Book bookRetrieved = this.bookService.findBookById(bookId);
            if (bookRetrieved == null) {
                throw new BookInvalidIdExc("Id not found - " + bookId);
            }
            return assembler.toModel(bookRetrieved);

        } catch (MethodArgumentTypeMismatchException e) {
            System.out.println("Error mismatch: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping("/book/specific")
    public Book getBookByTitleAndPublisher() {
        return bookService.findByTitleAndPublisher();
    }

    @GetMapping("/book/nogenre")
    public BookNoGenre getBookByTitleNoGenre() {
        return bookService.findByTitleNoGenre();
    }

    @GetMapping("/book/nopricegenre")
    public BookNoGenrePrice getBookByIdNoGenrePrice() {
        BookNoGenrePrice book = bookService.findByIdNoGenrePrice();
        System.out.println(book);
        return book;
    }

    @GetMapping("/book/price")
    public BigDecimal getBookByLengthOfTitle() {
        BigDecimal price = bookService.findBookPriceWherePublisherIs5Letters();
        return price;
    }

//    @PutMapping("/book")
//    public EntityModel<Book> updateBook(@Valid @RequestBody Book book) {
//        // call findById to ascertain whether it exists
//        Book bookRetrieved = this.bookService.findBookById(book.getId());
//        if (bookRetrieved == null) {
//            throw new BookInvalidIdExc("Id not found - " + book.getId());
//        }
//        this.bookService.saveBook(book);
//        return assembler.toModel(book);
//    }
//
//    @DeleteMapping("/book/{bookId}")
//    public String deleteBook(@PathVariable int bookId) {
//
//        try {
//            this.bookService.deleteBookById(bookId);
//            return "Book deleted - " + bookId;
//        }catch (EmptyResultDataAccessException e) {
//            System.out.println(e.getMessage());
//            throw e;
//        }
//
//    }
}
