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
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private List<Book> books;

    public Library( @NotEmpty String name,  String address) {

        this.name = name;
        this.address = address;

    }
}
