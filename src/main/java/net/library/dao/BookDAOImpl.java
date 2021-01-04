package net.library.dao;

import net.library.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{
    @Override
    public Book readBookById(Long bookId) {
        Connection connection = DataSource.getConnection();
        Book book = new Book();
        try{
            String sql = "SELECT * FROM book WHERE bookId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, bookId);
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                book.setName(result.getString("name"));
                book.setAuthorId(result.getLong("authorId"));
                book.setGenreId(result.getLong("genreId"));
                book.setPublisher(result.getString("publisher"));
                book.setYear(result.getInt("year"));
                book.setTown(result.getString("town"));
                book.setBookId(bookId);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> allBook() {
        Connection connection = DataSource.getConnection();
        List<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Book book = new Book();
                Long bookId = resultSet.getLong("bookId");
                String name = resultSet.getString("name");
                Long authorId = resultSet.getLong("authorId");
                Long genreId = resultSet.getLong("genreId");
                String publisher = resultSet.getString("publisher");
                int year = resultSet.getInt("year");
                String town = resultSet.getString("town");
                book.setBookId(bookId);
                book.setName(name);
                book.setAuthorId(authorId);
                book.setGenreId(genreId);
                book.setPublisher(publisher);
                book.setYear(year);
                book.setTown(town);
                books.add(book);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void addBook(Book book) {
        Connection connection = DataSource.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book " +
                    "(name, authorId, genreId, publisher, year, town) VALUES (?, ?, ?, ?, ?, ?);");
            statement.setString(1, book.getName());
            statement.setLong(2, book.getAuthorId());
            statement.setLong(3, book.getGenreId());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getYear());
            statement.setString(6, book.getTown());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Long id, Book book) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE book SET name = ?, " +
                    "authorId = ?, genreId = ?,publisher = ?,year = ?,town = ? WHERE bookId = ?;");
            statement.setString(1, book.getName());
            statement.setLong(2, book.getAuthorId());
            statement.setLong(3, book.getGenreId());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getYear());
            statement.setString(6, book.getTown());
            statement.setLong(7, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(Long id) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE bookId = ?;");
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
    public List<Book> filterBook(Book book) {
        Connection connection = DataSource.getConnection();
        List<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM book WHERE name " +
                    "LIKE ?, authorId = ?, publisher = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "\'%" + book.getName() + "%\'");
            statement.setLong(2, book.getAuthorId());
            statement.setString(3, book.getPublisher());
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Book fBook = new Book();
                Long bookId = resultSet.getLong("bookId");
                String name = resultSet.getString("name");
                Long authorId = resultSet.getLong("authorId");
                Long genreId = resultSet.getLong("genreId");
                String publisher = resultSet.getString("publisher");
                int year = resultSet.getInt("year");
                String town = resultSet.getString("town");
                fBook.setBookId(bookId);
                fBook.setName(name);
                fBook.setAuthorId(authorId);
                fBook.setGenreId(genreId);
                fBook.setPublisher(publisher);
                fBook.setYear(year);
                fBook.setTown(town);
                books.add(fBook);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }
}
