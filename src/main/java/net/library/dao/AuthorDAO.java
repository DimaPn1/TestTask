package net.library.dao;

import net.library.models.Author;

import java.util.List;

public interface AuthorDAO {

    Author readAuthorById(Long authorID);
    List<Author> allAuthor();
    void addAuthor(Author author);
    void updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
