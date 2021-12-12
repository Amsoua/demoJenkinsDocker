package com.tuto.docker.jenkins.demo.controller;

import com.tuto.docker.jenkins.demo.entity.Book;
import com.tuto.docker.jenkins.demo.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Book> getLibraries() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid library id %s", id)));
    }

    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@Valid @RequestBody Book library) {
        return bookRepository.save(library);
    }
}
