package com.halversondm.demo.graphql;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoGraphqlApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoGraphqlApplicationTests.class);

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void executeGraphQL_WhenTypical() {
        String request = "{\n" +
                "\"query\": \"{bookById(id: \\\"book-1\\\"){id name pageCount author { firstName lastName } }}\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
        String response = testRestTemplate.postForObject("http://localhost:" + port + "/graphql", httpEntity, String.class);
        LOGGER.info("the response {}", response);
        assertTrue(response.contains("book-1"));
        assertTrue(response.contains("Rowling"));
    }

}
