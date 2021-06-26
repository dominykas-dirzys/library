package com.library.library.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.library.library.AbstractIntegration;
import com.library.library.models.dto.User;
import com.library.library.models.entities.BookEntity;
import com.library.library.models.entities.UserEntity;
import com.library.library.repositories.BookRepository;
import com.library.library.repositories.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
class UserControllerIT extends AbstractIntegration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getUsers() throws Exception {
        List<UserEntity> existingUsers = userRepository.findAll();

        UserEntity user = new UserEntity();
        user.setName("Test");
        userRepository.save(user);

        List<User> result = sendGet("/api/users", new TypeReference<List<User>>() {
        });

        assertEquals(existingUsers.size() + 1, result.size());
    }

    @Test
    void userTakingABook() throws Exception {
        UserEntity user = new UserEntity();
        user.setName("Test");
        userRepository.save(user);

        BookEntity newBook = new BookEntity();
        newBook.setName("Test");
        newBook.setAuthor("Test Author");
        newBook.setCategory("Test Category");
        newBook.setLanguage("Test Language");
        newBook.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook.setIsbn("900-0-0000-0000-0");
        newBook.setUser(user);
        bookRepository.save(newBook);

        BookEntity newBook2 = new BookEntity();
        newBook2.setName("Test 2");
        newBook2.setAuthor("Test Author");
        newBook2.setCategory("Test Category");
        newBook2.setLanguage("Test Language");
        newBook2.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook2.setIsbn("900-0-0000-0000-1");
        newBook2.setUser(user);
        bookRepository.save(newBook2);

        BookEntity newBook3 = new BookEntity();
        newBook3.setName("Test 3");
        newBook3.setAuthor("Test Author");
        newBook3.setCategory("Test Category");
        newBook3.setLanguage("Test Language");
        newBook3.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook3.setIsbn("900-0-0000-0000-2");
        newBook3.setUser(user);
        bookRepository.save(newBook3);

        BookEntity newBook4 = new BookEntity();
        newBook4.setName("Test 4");
        newBook4.setAuthor("Test Author");
        newBook4.setCategory("Test Category");
        newBook4.setLanguage("Test Language");
        newBook4.setPublicationDate(LocalDate.of(2000, 1, 1));
        newBook4.setIsbn("900-0-0000-0000-3");
        bookRepository.save(newBook4);

        String result = sendGet("/api/users/" + user.getId() + "/take/" + newBook4.getId() + "/" + 2, new TypeReference<String>() {
        });

        assertEquals("Error", result);
    }
}
