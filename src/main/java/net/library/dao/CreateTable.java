package net.library.dao;

import net.library.models.Author;
import net.library.models.Book;
import net.library.models.Genre;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    private static final BookDAO bookDAO = new BookDAOImpl();
    private static final GenreDAO genreDAO = new GenreDAOImpl();
    private static final AuthorDAO authorDAO = new AuthorDAOImpl();

    public static void CreateAndFillingTable(){
        genreTable();
        authorTable();
        bookTable();
        fillingGenre();
        fillingAuthor();
        fillingBook();
    }
    public static void genreTable(){
        Connection connection = DataSource.getConnection();
        String sql = "CREATE TABLE genre" +
                "(genreId BIGINT IDENTITY PRIMARY KEY ," +
                "name VARCHAR(50));";
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate("SHUTDOWN;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fillingGenre(){
        if(genreDAO.allGenre().size() == 0) {
            String[] GenreName = new String[]{"Fiction", "Non—fiction", "Light fiction", "Business", "Travel", "Autobiography", "Fantasy"};

            for (int i = 0; i < 7; i++) {
                Genre genre = new Genre(GenreName[i]);
                genreDAO.addGenre(genre);
            }
        }
    }

    public static void authorTable(){
        Connection connection = DataSource.getConnection();
        String sql = "CREATE TABLE author" +
                "(authorId BIGINT IDENTITY PRIMARY KEY ," +
                "name VARCHAR(100) NOT NULL ," +
                "lastname VARCHAR(100) NOT NULL ," +
                "patronymic VARCHAR(100) NOT NULL);";
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate("SHUTDOWN;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillingAuthor(){
        if(authorDAO.allAuthor().size() == 0) {
            String[] AuthorName = new String[]{"Aleksandr", "Andrey", "Anna", "Alina", "Boris", "Vera", "Timur"};
            String[] AuthorLastname = new String[]{"Chistovich", "Kuznetsov", "Tsvetaeva", "Ivanova", "Djumayev", "Murzina", "Hasymov"};
            String[] AuthorPatronymic = new String[]{"Anatolyevich", "Victorovich", "Yuryevna", "Nikolayevna", "Vyacheslavovich", "Mikhaylovna", "Vladimirovich"};

            for (int i = 0; i < 7; i++) {
                Author author = new Author(AuthorName[i], AuthorLastname[i], AuthorPatronymic[i]);
                authorDAO.addAuthor(author);
            }
        }
    }
    public static void bookTable(){
        Connection connection = DataSource.getConnection();
        String sql = "CREATE TABLE book" +
                "(bookId BIGINT IDENTITY PRIMARY KEY ," +
                "name VARCHAR(100) NOT NULL ," +
                "authorId BIGINT NOT NULL ," +
                "genreId BIGINT NOT NULL ," +
                "publisher VARCHAR(50) NOT NULL ," +
                "year INT NOT NULL ," +
                "town VARCHAR(50) NOT NULL);";
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate("SHUTDOWN;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillingBook(){
        if(bookDAO.allBook().size() == 0) {

            String[] BookName = new String[]{"Dead Souls", "The twelve Chairs", "Auditor", "Run", "Morphine", "Hunter's Notes", "Prisoner of the Caucasus"};
            Long[] AuthorId = new Long[]{2l, 3l, 1l, 5l, 2l, 1l, 2l};
            Long[] GenreId = new Long[]{3l, 1l, 3l, 7l, 1l, 6l, 6l};
            String[] Publisher = new String[]{"Moscow", "St.Petersburg", "Moscow", "Moscow", "St.Petersburg"
                    , "O`Reilly", "O`Reilly"};
            int[] Year = new int[]{2001, 1998, 1987, 2011, 2018, 2020, 2012};
            String[] Town = new String[]{"Moscow", "St.Petersburg", "Moscow", "Moscow", "St.Petersburg"
                    , "Moscow", "St.Petersburg"};

            for (int i = 0; i < 7; i++) {
                Book book = new Book(BookName[i], AuthorId[i], GenreId[i],
                        Publisher[i], Year[i], Town[i]);
                bookDAO.addBook(book);
            }
        }
    }
}
