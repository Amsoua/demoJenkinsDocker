package com.tuto.docker.jenkins.demo.controller;

import com.tuto.docker.jenkins.demo.entity.Library;
import com.tuto.docker.jenkins.demo.repository.LibraryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class LibraryController {
    private final LibraryRepository libraryRepository;


    public LibraryController(LibraryRepository libraryRepository) {

        this.libraryRepository = libraryRepository;

    }

    @GetMapping(value = "/libraries", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Library> getLibraries() {
        return libraryRepository.findAll();
    }

    @GetMapping(value = "/library/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Library getLibrary(@PathVariable long id){
        return libraryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid library id %s", id)));
    }

    @PostMapping(value = "/library", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Library createLibrary(@Valid @RequestBody Library library) {
        return libraryRepository.save(library);
    }
}
