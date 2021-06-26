package com.library.library.repositories;

import com.library.library.models.entities.BookEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<BookEntity, Long> {
    List<BookEntity> findAll();

    BookEntity save(BookEntity book);

    void deleteById(Long id);

    List<BookEntity> findAllByNameContains(String name);

    List<BookEntity> findAllByAuthorContains(String author);

    BookEntity findById(Long id);

    List<BookEntity> findAllByCategoryContains(String category);

    List<BookEntity> findAllByLanguageContains(String language);

    List<BookEntity> findAllByIsbnContains(String isbn);

    List<BookEntity> findAllByUserNull();

    List<BookEntity> findAllByUserNotNull();

    List<BookEntity> findAllByUserId(Long id);
}
