package com.library.library.repositories;

import com.library.library.models.entities.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserEntity, Long> {
    List<UserEntity> findAll();

    UserEntity save(UserEntity userEntity);

    void deleteById(Long id);

    UserEntity findOneById(Long id);
}
