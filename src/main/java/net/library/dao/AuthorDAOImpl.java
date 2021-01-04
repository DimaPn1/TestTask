package net.library.dao;

import net.library.models.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO{

    @Override
    public Author readAuthorById(Long authorID) {
        Connection connection = DataSource.getConnection();
        Author author = new Author();
        try{
            String sql = "SELECT * FROM author WHERE authorID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, authorID);
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                author.setName(result.getString("name"));
                author.setLastname(result.getString("lastname"));
                author.setPatronymic(result.getString("patronymic"));
                author.setAuthorId(authorID);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
//
    @Override
    public List<Author> allAuthor() {
        Connection connection = DataSource.getConnection();
        List<Author> authors = new ArrayList<>();
        try{
            String sql = "SELECT * FROM author";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Author author = new Author();
                Long authorId = resultSet.getLong("authorId");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String patronymic = resultSet.getString("patronymic");
                author.setAuthorId(authorId);
                author.setName(name);
                author.setLastname(lastname);
                author.setPatronymic(patronymic);
                authors.add(author);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
//
    @Override
    public void addAuthor(Author author) {
        Connection connection = DataSource.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO author " +
                    "(name, lastname, patronymic) VALUES (?, ?, ?);");
            statement.setString(1, author.getName());
            statement.setString(2, author.getLastname());
            statement.setString(3, author.getPatronymic());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE author SET name = ?, lastname = ?, patronymic = ? WHERE authorId = ?;");
            statement.setString(1, author.getName());
            statement.setString(2, author.getLastname());
            statement.setString(3, author.getPatronymic());
            statement.setLong(4, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        Connection connection = DataSource.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM author WHERE authorId = ?;");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.executeUpdate("SHUTDOWN;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
