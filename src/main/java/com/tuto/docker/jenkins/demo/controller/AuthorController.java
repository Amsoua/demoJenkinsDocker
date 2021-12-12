package com.tuto.docker.jenkins.demo.controller;

import com.tuto.docker.jenkins.demo.entity.Author;
import com.tuto.docker.jenkins.demo.repository.AuthorRepository;
import com.tuto.docker.jenkins.demo.repository.BookRepository;
import com.tuto.docker.jenkins.demo.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Author> getLibraries() {
        return authorRepository.findAll();
    }

    @GetMapping(value = "/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author getAuthor(@PathVariable long id){
        return authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid Author id %s", id)));
    }

    @PostMapping(value = "/author", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@Valid @RequestBody Author Author) {
        return authorRepository.save(Author);
    }
}
