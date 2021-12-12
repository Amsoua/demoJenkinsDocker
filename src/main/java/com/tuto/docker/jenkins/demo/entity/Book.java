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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBook;
    @NotEmpty
    private String name;
    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author>  author;

    public Book(Long idBook, @NotEmpty String name, @NotEmpty List<Author> author) {
        this.idBook = idBook;
        this.name = name;
        this.author = author;
    }
}
