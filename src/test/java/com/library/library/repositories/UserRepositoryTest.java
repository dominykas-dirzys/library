package com.library.library.repositories;

import com.library.library.models.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        UserEntity newUser = new UserEntity();
        newUser.setName("Test");

        userRepository.save(newUser);
    }

    @Test
    void save() {
        assertNotNull(userRepository.findAll());
        assertEquals("Test", userRepository.findAll().get(0).getName());
    }

    @Test
    void findAll() {
        List<UserEntity> result = userRepository.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void deleteById() {
        List<UserEntity> users = userRepository.findAll();
        int originalSize = users.size();

        userRepository.deleteById(users.get(0).getId());
        int result = userRepository.findAll().size();

        assertEquals(originalSize - 1, result);
    }

    @Test
    void findOneById() {
        UserEntity result = userRepository.findOneById(userRepository.findAll().get(0).getId());

        assertNotNull(result);
        assertEquals("Test", result.getName());
    }
}
