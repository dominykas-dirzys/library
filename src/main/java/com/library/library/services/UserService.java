package com.library.library.services;

import com.library.library.converters.UserConverter;
import com.library.library.models.dto.User;
import com.library.library.models.entities.BookEntity;
import com.library.library.models.entities.UserEntity;
import com.library.library.repositories.BookRepository;
import com.library.library.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, UserConverter userConverter, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.bookRepository = bookRepository;
    }

    public List<User> getUsers() {
        return userConverter.convert(userRepository.findAll());
    }

    public User save(User user) {
        UserEntity userEntity = userConverter.convertToEntity(user);
        userRepository.save(userEntity);

        return userConverter.convert(userEntity);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userConverter.convert(userRepository.findOneById(id));
    }

    public String takeValidBook(Long userId, Long bookId, int months) {
        int noOfUsersTakenBooks = bookRepository.findAllByUserId(userId).size();
        BookEntity takenBook = bookRepository.findById(bookId);
        UserEntity activeUser = userRepository.findOneById(userId);
        if (0 < months && months <= 2 && noOfUsersTakenBooks < 3) {
            takenBook.setUser(activeUser);
            bookRepository.save(takenBook);
            return activeUser.getName() + " took \"" + takenBook.getName() + "\" for " + months + " months.";
        } else if (0 > months || months > 2 && noOfUsersTakenBooks >= 3) {
            return "\"Error\": \"Book cannot be taken for that long and user " + activeUser.getName() + " has too many books.\"";
        } else if (0 > months || months > 2) {
            return "\"Error\": \"Book cannot be taken for that long.\"";
        } else if (noOfUsersTakenBooks >= 3) {
            return "\"Error\": " + "\"" + activeUser.getName() + " has too many books.\"";
        } else {
            return "\"Error\": \"Error has occurred\"";
        }
    }
}
