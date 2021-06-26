package com.library.library.repositories;

import com.library.library.models.dto.User;
import com.library.library.models.entities.BookEntity;
import com.library.library.models.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        BookEntity newBook = new BookEntity();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook.setIsbn("900-0-0000-0000-0");

        bookRepository.save(newBook);
    }

    @Test
    void save() {
        assertNotNull(bookRepository.findAll());
        assertEquals("Test", bookRepository.findAll().get(0).getName());
    }

    @Test
    void findAll() {
        List<BookEntity> result = bookRepository.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void deleteById() {
        List<BookEntity> books = bookRepository.findAll();
        int originalSize = books.size();

        bookRepository.deleteById(books.get(0).getId());
        int result = bookRepository.findAll().size();

        assertEquals(originalSize - 1, result);
    }

    @Test
    void findAllByNameContains() {
        List<BookEntity> result = bookRepository.findAllByNameContains("Te");

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findAllByAuthorContains() {
        List<BookEntity> result = bookRepository.findAllByAuthorContains("Test Au");

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findById() {
        BookEntity result = bookRepository.findById(bookRepository.findAll().get(0).getId());

        assertNotNull(result);
        assertEquals("Test", result.getName());
    }

    @Test
    void findAllByCategoryContains() {
        List<BookEntity> result = bookRepository.findAllByCategoryContains("Test Ca");

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findAllByLanguageContains() {
        List<BookEntity> result = bookRepository.findAllByLanguageContains("Test La");

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findAllByIsbnContains() {
        List<BookEntity> result = bookRepository.findAllByIsbnContains("00");

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findAllByUserNull() {
        List<BookEntity> result = bookRepository.findAllByUserNull();

        assertNotNull(result);
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void findAllByUserNotNull() {
        List<BookEntity> result = bookRepository.findAllByUserNotNull();

        assertEquals(0, result.size());
    }

    @Test
    void findAllByUserId() {
        BookEntity newBook2 = new BookEntity();
        newBook2.setName("Test 2");
        newBook2.setAuthor("Test Author");
        newBook2.setCategory("Test Category");
        newBook2.setLanguage("Test Language");
        newBook2.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook2.setIsbn("900-0-0000-0000-0");
        UserEntity newUser = new UserEntity();
        newUser.setName("Test User");
        userRepository.save(newUser);
        newBook2.setUser(newUser);
        bookRepository.save(newBook2);

        List<BookEntity> result = bookRepository.findAllByUserId(newUser.getId());

        assertNotNull(result);
        assertEquals("Test 2", result.get(0).getName());
    }
}
