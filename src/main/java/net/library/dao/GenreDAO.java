package net.library.dao;

import net.library.models.Book;
import net.library.models.Genre;

import java.util.List;

public interface GenreDAO {

    Genre readGenreById(Long genreId);
    List<Genre> allGenre();
    void addGenre(Genre genre);
    void updateGenre(Long id, Genre genre);
    void deleteGenre(Long id);
    List<Book> bookStatistics(Long id);
}
