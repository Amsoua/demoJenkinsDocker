package com.tuto.docker.jenkins.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAuthor;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private String address;
    @ManyToMany(mappedBy = "author")
    private List<Book> listBooks;

    public Author(Long idAuthor, @NotEmpty String name, @NotEmpty String email, String address, List<Book> listBooks) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.email = email;
        this.address = address;
        this.listBooks = listBooks;
    }
}
