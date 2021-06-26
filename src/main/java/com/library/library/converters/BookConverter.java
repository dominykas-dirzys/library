package com.library.library.converters;

import com.library.library.models.dto.Book;
import com.library.library.models.dto.User;
import com.library.library.models.entities.BookEntity;
import com.library.library.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookConverter extends AbstractBiConverter<BookEntity, Book> {
    private final BookRepository bookRepository;
    private final UserConverter userConverter;

    public BookConverter(BookRepository bookRepository, UserConverter userConverter) {
        this.bookRepository = bookRepository;
        this.userConverter = userConverter;
    }

    public Book convert(BookEntity book) {
        Book result = new Book(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getLanguage(),
                book.getPublicationDate().toString(),
                book.getIsbn()
        );
        if (book.getUser() != null) {
            result.setUser(userConverter.convert(book.getUser()));
        }
        return result;
    }

    public BookEntity convertToEntity(Book book) {
        BookEntity result;
        if (book.getId() == null) {
            result = new BookEntity();
        } else {
            result = bookRepository.findById(book.getId());
        }

        result.setName(book.getName());
        result.setAuthor(book.getAuthor());
        result.setCategory(book.getCategory());
        result.setLanguage(book.getLanguage());
        result.setPublicationDate(LocalDate.parse(book.getPublicationDate()));
        result.setIsbn(book.getIsbn());
        result.setUser(book.getUser() == null ? null : userConverter.convertToEntity(book.getUser()));

        return result;
    }
}
