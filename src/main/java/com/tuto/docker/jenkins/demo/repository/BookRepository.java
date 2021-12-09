package com.tuto.docker.jenkins.demo.repository;

import com.tuto.docker.jenkins.demo.entity.Book;
import com.tuto.docker.jenkins.demo.entity.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
}
