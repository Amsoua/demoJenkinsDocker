package com.tuto.docker.jenkins.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAuthor;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private String address;
    @ManyToMany(mappedBy = "authorsOfBooks")
    private List<Book> listBooks;

}
