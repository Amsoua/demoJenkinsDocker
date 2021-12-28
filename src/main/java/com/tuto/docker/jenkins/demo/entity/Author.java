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
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> listBooks;

    public Author( @NotEmpty String firstName,@NotEmpty String lastName, @NotEmpty String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;

    }
}
