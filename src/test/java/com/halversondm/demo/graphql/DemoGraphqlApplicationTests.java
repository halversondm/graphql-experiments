package com.halversondm.demo.graphql;

import com.halversondm.demo.graphql.entity.Author;
import com.halversondm.demo.graphql.entity.Book;
import com.halversondm.demo.graphql.repository.AuthorRepository;
import com.halversondm.demo.graphql.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoGraphqlApplicationTests {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void authorRepository_WhenTypical() {
        List<Author> authors = authorRepository.findAll();
        assertTrue(authors.size() == 3);
    }

    @Test
    void bookRepository_WhenTypical() {
        List<Book> books = bookRepository.findAll();
        assertTrue(books.size() == 3);
    }


}
