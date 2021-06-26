package com.library.library.controllers;

import com.library.library.models.dto.User;
import com.library.library.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}/take/{bookId}/{months}")
    public String userTakingABook(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable int months) {
        return userService.takeValidBook(userId, bookId, months);
    }
}
