package com.tuto.docker.jenkins.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private String address;
    @OneToMany
    private List<Book> books;

    public Library(Long id, @NotEmpty String name, String description, String address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.books = books;
    }
}
