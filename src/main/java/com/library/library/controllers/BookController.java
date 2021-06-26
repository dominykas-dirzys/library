package com.library.library.controllers;

import com.library.library.models.dto.Book;
import com.library.library.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/library")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("filter_by_name/{name}")
    public List<Book> getBookByName(@PathVariable String name) {
        return bookService.getBookByName(name);
    }

    @GetMapping("filter_by_author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("filter_by_category/{category}")
    public List<Book> getBookByCategory(@PathVariable String category) {
        return bookService.getBookByCategory(category);
    }

    @GetMapping("filter_by_isbn/{isbn}")
    public List<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("filter_by_language/{language}")
    public List<Book> getBookByLanguage(@PathVariable String language) {
        return bookService.getBookByLanguage(language);
    }

    @GetMapping("taken_books")
    public List<Book> getTakenBooks() {
        return bookService.getTakenBooks();
    }

    @GetMapping("available_books")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
