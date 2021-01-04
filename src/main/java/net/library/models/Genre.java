package net.library.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Genre {

    private Long genreId;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
