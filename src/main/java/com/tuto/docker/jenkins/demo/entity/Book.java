package com.tuto.docker.jenkins.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBook;
    @NotEmpty
    private String name;
    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author>  author;
}
