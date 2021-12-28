package com.tuto.docker.jenkins.demo;


import com.tuto.docker.jenkins.demo.entity.Author;
import com.tuto.docker.jenkins.demo.entity.Book;
import com.tuto.docker.jenkins.demo.entity.Library;
import com.tuto.docker.jenkins.demo.repository.AuthorRepository;
import com.tuto.docker.jenkins.demo.repository.BookRepository;
import com.tuto.docker.jenkins.demo.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;

@SpringBootApplication
public class DemoJenkenDockerApplication {

	private static final Logger log =
			LoggerFactory.getLogger(DemoJenkenDockerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoJenkenDockerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepo, AuthorRepository authRepo, LibraryRepository libRepo) {
		return (args) -> {
			//create a new author
			Author author = new Author("Rowling", "Gosling", "rogoslin@gmail.com",
					"2, 3477 Obama Street");
			//create a new book
			Book book = new Book("Harry Potter");

			//create a new library
			Library library = new Library("Amsoua Library","3455 Avenue Amadou SOUANE, Quebec");
			//save author to db
			authRepo.save(author);
			//save library to db
			libRepo.save(library);
			//associate author with book
			book.setAuthor(author);
			book.setLibrary(library);
			//save book
			bookRepo.save(book);
			//read book from db with custom findByTitle
			List<Book> savedBook = bookRepo.findByTitle("Harry Potter");
			//print title
			log.info(savedBook.get(0).getTitle());
			//print book author's full name
			log.info(savedBook.get(0).getAuthor().getFirstName());
		};
	}

}
