package com.chung.polarbookshop.catalogservice.web;

import com.chung.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenPostRequestThenBookCreated() {
        System.out.println("whenPostRequestThenBookCreated");
        Book expectedBook = new Book("1231231231", "Title", "Author", Year.of(1991));
        ResponseEntity<Book> response = restTemplate.postForEntity("/books", expectedBook, Book.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isNotNull()
                .isEqualToComparingFieldByField(expectedBook);
    }
}
