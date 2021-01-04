package net.library.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Author {

    private Long authorId;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String name;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String lastname;

    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 30, message = "In the range from 2 to 30 characters")
    private String patronymic;

    public Author(String name, String lastname, String patronymic) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
    }

    public Author() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
