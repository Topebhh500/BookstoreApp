package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bookstore.domain.Book;
import com.example.bookstore.repository.BookRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            repository.save(new Book("Effective Java", "Joshua Bloch", 2008, "978-0134685991", 45.00));
            repository.save(new Book("Clean Code", "Robert C. Martin", 2008, "978-0132350884", 40.00));
            repository.save(new Book("Java Concurrency in Practice", "Brian Goetz", 2006, "978-0321349606", 50.00));

			log.info("fetch all students");
			for (Book  book : repository.findAll()) {
				log.info(book.toString());
			}
        };
    }
}
