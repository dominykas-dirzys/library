package com.library.library.converters;

import com.library.library.models.dto.User;
import com.library.library.models.entities.UserEntity;
import com.library.library.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractBiConverter<UserEntity, User> {
    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User convert(UserEntity user) {
        return new User(
            user.getId(),
            user.getName()
        );
    }

    public UserEntity convertToEntity(User user) {
        UserEntity result = new UserEntity();

        if (user.getId() != null) {
            result = userRepository.findOneById(user.getId());
        }

        if (user.getName() != null) {
            result.setName(user.getName());
        }

        return result;
    }
}
