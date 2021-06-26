package com.library.library.models.dto;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String category;
    private String language;
    private String publicationDate;
    private String isbn;
    private User user;

    public Book() {
    }

    public Book(Long id, String name, String author, String category, String language, String publicationDate, String isbn) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
    }

    public Book(Long id, String name, String author, String category, String language, String publicationDate, String isbn, User user) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
