package com.tuto.docker.jenkins.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBook;
    @NotEmpty
    private String title;
    @NotEmpty
    @ManyToOne(fetch = FetchType.EAGER)
    private Author  author;
    @ManyToOne(fetch = FetchType.EAGER)
    private Library library;

    public Book(@NotEmpty String title) {

        this.title = title;

    }
}
