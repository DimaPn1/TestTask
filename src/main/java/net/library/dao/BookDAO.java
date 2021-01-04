package net.library.dao;

import net.library.models.Author;
import net.library.models.Book;

import java.util.List;

public interface BookDAO {

    Book readBookById(Long bookId);
    List<Book> allBook();
    void addBook(Book book);
    void updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> filterBook(Book book);
}
