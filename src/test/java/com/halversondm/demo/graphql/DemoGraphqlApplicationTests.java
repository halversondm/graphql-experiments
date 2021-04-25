package com.halversondm.demo.graphql;

import com.halversondm.demo.graphql.entity.Author;
import com.halversondm.demo.graphql.entity.Book;
import com.halversondm.demo.graphql.repository.AuthorRepository;
import com.halversondm.demo.graphql.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoGraphqlApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoGraphqlApplicationTests.class);

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void executeGraphQL_WhenBookById() {
        String request = "{\n" +
                "\"query\": \"{bookById(id: \\\"book-1\\\"){id name pageCount author { firstName lastName } }}\"\n" +
                "}";
        String response = makeCall(request);
        assertTrue(response.contains("book-1"));
        assertTrue(response.contains("Rowling"));
    }

    @Test
    void executeGraphQL_WhenAuthorLastName() {
        String request = "{\"query\": \"{authorByLastName(lastName: \\\"Rowling\\\") {lastName firstName}}\"}";
        String response = makeCall(request);
        assertTrue(response.contains("Rowling"));
    }

    @Test
    void executeGraphQL_WhenAuthors() {
        String request = "{\"query\": \"{authors{lastName firstName}}\"}";
        String response = makeCall(request);
        assertTrue(response.contains("Rowling"));
        assertTrue(response.contains("Rice"));
        assertTrue(response.contains("Melville"));
    }

    @Test
    void executeGraphQL_WhenBooks() {
        String request = "{\"query\": \"{books {name pageCount}}\"}";
        String response = makeCall(request);
        assertTrue(response.contains("Potter"));
        assertTrue(response.contains("Moby"));
        assertTrue(response.contains("Interview"));
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

    private String makeCall(String request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
        String response = testRestTemplate.postForObject("http://localhost:" + port + "/graphql", httpEntity, String.class);
        LOGGER.info("the response {}", response);
        return response;
    }

}
