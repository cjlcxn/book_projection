package com.test.bookstoreapp.controller.hateaosEntity;

import com.test.bookstoreapp.entity.Book;
import com.test.bookstoreapp.controller.BookController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

    // toModel is a custom assembler, that takes singular Books, and returns an EntityModel
    @Override
    public EntityModel<Book> toModel(Book book) {

        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks()).withRel("allBooks"));
    }
}
