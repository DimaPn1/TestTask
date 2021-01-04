package net.library.models;

import javax.validation.constraints.*;

public class Book {
    private Long bookId;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String name;

    @PositiveOrZero(message = "The value must be positive or zero")
    @NotEmpty(message = "Field must be filled")
    private Long authorId;

    @PositiveOrZero(message = "The value must be positive or zero")
    @NotEmpty(message = "Field must be filled")
    private Long genreId;

    @NotEmpty(message = "Field must be filled")
    private String publisher;

    @Positive(message = "The value must be positive")
    @Max(value = 2020, message = "The value no more than 2020")
    @NotEmpty(message = "Field must be filled")
    private int year;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String town;

    public Book(String name, Long authorId,
                Long genreId, String publisher, int year, String town) {
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publisher = publisher;
        this.year = year;
        this.town = town;
    }

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
