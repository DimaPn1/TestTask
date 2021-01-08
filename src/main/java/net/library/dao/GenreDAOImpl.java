package net.library.dao;

import net.library.models.Author;
import net.library.models.Book;
import net.library.models.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO{

    @Override
    public Genre readGenreById(Long genreId) {
        Connection connection = DataSource.getConnection();
        Genre genre = new Genre();
        try{
            String sql = "SELECT * FROM genre WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, genreId);
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                genre.setName(result.getString("name"));
                genre.setGenreId(genreId);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public List<Genre> allGenre() {
        Connection connection = DataSource.getConnection();
        List<Genre> genres = new ArrayList<>();
        try{
            String sql = "SELECT * FROM genre";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Genre genre = new Genre();
                Long genreId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                genre.setGenreId(genreId);
                genre.setName(name);
                genres.add(genre);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public void addGenre(Genre genre) {
        Connection connection = DataSource.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO genre " +
                    "(name) VALUES (?);");
            statement.setString(1, genre.getName());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGenre(Long id, Genre genre) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE genre SET name = ? WHERE genreId = ?;");
            statement.setString(1, genre.getName());
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGenre(Long id) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM genre WHERE id = ?;");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.executeUpdate("SHUTDOWN;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Book> bookStatistics(Long id){
        Connection connection = DataSource.getConnection();
        List<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM book LEFT JOIN genre ON book.genreId = genre.id;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Book fBook = new Book();
                Long genreId = res.getLong("genre.id");
                if(genreId.equals(id)){
                    Long bookId = res.getLong("book.id");
                    String name = res.getString("book.name");
                    Long authorId = res.getLong("book.authorId");
                    String publisher = res.getString("book.publisher");
                    int year = res.getInt("book.year");
                    String town = res.getString("book.town");
                    fBook.setBookId(bookId);
                    fBook.setName(name);
                    fBook.setAuthorId(authorId);
                    fBook.setGenreId(genreId);
                    fBook.setPublisher(publisher);
                    fBook.setYear(year);
                    fBook.setTown(town);
                    books.add(fBook);
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
