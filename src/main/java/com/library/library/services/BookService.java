package com.library.library.services;

import com.library.library.converters.BookConverter;
import com.library.library.models.dto.Book;
import com.library.library.models.entities.BookEntity;
import com.library.library.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public BookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public List<Book> getBooks() {
        return bookConverter.convert(bookRepository.findAll());
    }

    public Book save(Book book) {
        BookEntity bookEntity = bookConverter.convertToEntity(book);
        bookRepository.save(bookEntity);

        return bookConverter.convert(bookEntity);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookConverter.convert(bookRepository.findById(id));
    }

    public List<Book> getBookByName(String name) {
        return bookConverter.convert(bookRepository.findAllByNameContains(name));
    }

    public List<Book> getBookByAuthor(String author) {
        return bookConverter.convert(bookRepository.findAllByAuthorContains(author));
    }

    public List<Book> getBookByCategory(String category) {
        return bookConverter.convert(bookRepository.findAllByCategoryContains(category));
    }

    public List<Book> getBookByLanguage(String language) {
        return bookConverter.convert(bookRepository.findAllByLanguageContains(language));
    }

    public List<Book> getBookByIsbn(String isbn) {
        return bookConverter.convert(bookRepository.findAllByIsbnContains(isbn));
    }

    public List<Book> getTakenBooks() {
        return bookConverter.convert(bookRepository.findAllByUserNotNull());
    }

    public List<Book> getAvailableBooks() {
        return bookConverter.convert(bookRepository.findAllByUserNull());
    }

    public List<Book> getBooksByUserId(Long id) {
        return bookConverter.convert(bookRepository.findAllByUserId(id));
    }
}
