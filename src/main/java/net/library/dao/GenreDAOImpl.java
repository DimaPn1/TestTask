package net.library.dao;

import net.library.models.Author;
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
            String sql = "SELECT * FROM genre WHERE genreID = ?";
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
                Long genreId = resultSet.getLong("genreId");
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM genre WHERE genreId = ?;");
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
    public int bookStatistics(Long id){
        Connection connection = DataSource.getConnection();
        int result = 0;
        try{
            String sql = "SELECT * FROM book WHERE genreId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                result++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
