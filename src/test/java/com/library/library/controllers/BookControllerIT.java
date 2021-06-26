package com.library.library.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.library.library.AbstractIntegration;
import com.library.library.models.dto.Book;
import com.library.library.models.entities.BookEntity;
import com.library.library.repositories.BookRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
class BookControllerIT extends AbstractIntegration {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getBooks() throws Exception {
        List<BookEntity> existingBooks = bookRepository.findAll();

        BookEntity newBook = new BookEntity();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook.setIsbn("900-0-0000-0000-0");
        bookRepository.save(newBook);

        List<Book> result = sendGet("/api/library", new TypeReference<List<Book>>() {
        });

        assertEquals(existingBooks.size() + 1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void getBook() throws Exception {
        BookEntity newBook = new BookEntity();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook.setIsbn("900-0-0000-0000-0");
        bookRepository.save(newBook);

        Book result = sendGet("/api/library/" + newBook.getId(), new TypeReference<Book>() {
        });

        assertEquals(newBook.getId(), result.getId());
        assertEquals("Test", result.getName());
        assertEquals("Test Author", result.getAuthor());
    }

    @Test
    void save() throws Exception {
        Book newBook = new Book();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate("2000-01-01");
        newBook.setIsbn("900-0-0000-0000-0");

        Book result = sendPost("/api/library/", newBook, new TypeReference<Book>() {
        });

        assertNotNull(result.getId());
        assertEquals("Test", result.getName());
        assertEquals("Test Author", result.getAuthor());
    }

    @Test
    void delete() throws Exception {
        int numberOfBooks = bookRepository.findAll().size();

        BookEntity newBook = new BookEntity();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook.setIsbn("900-0-0000-0000-0");
        bookRepository.save(newBook);

        Boolean result = sendDelete("/api/library/" + newBook.getId(), new TypeReference<Boolean>() {
        });

        assertTrue(result);
        assertEquals(numberOfBooks, bookRepository.findAll().size());
    }
}
