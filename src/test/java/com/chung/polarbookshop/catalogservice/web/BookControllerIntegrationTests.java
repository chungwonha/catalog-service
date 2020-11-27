package com.chung.polarbookshop.catalogservice.web;

import com.chung.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class BookControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenPostRequestThenBookCreated() {
        System.out.println("*******************whenPostRequestThenBookCreated");
        Book expectedBook = new Book("1231231231", "Title", "Author", Year.of(1991),12.90);
        ResponseEntity<Book> response = restTemplate.postForEntity("/books", expectedBook, Book.class);

        Book returnedBook = response.getBody();
        expectedBook.setId(returnedBook.getId());
        expectedBook.setCreatedDate(returnedBook.getCreatedDate());
        expectedBook.setLastModifiedDate(returnedBook.getLastModifiedDate());
        expectedBook.setVersion(returnedBook.getVersion());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isNotNull()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @Test
    void whenGetRequestWithIdThenBookReturned(){
        System.out.println("***************whenGetRequestWithIdThenBookReturned");
        String bookIsbn = "1231231230";
        Book expectedBook = new Book(bookIsbn, "Book Title", "Book Author", Year.of(1991), 9.90);
        restTemplate.postForEntity("/books", expectedBook, Book.class);

        ResponseEntity<Book> response = restTemplate.getForEntity("/books/" + bookIsbn, Book.class);
        Book returnedBook = response.getBody();
        System.out.println("ISBN from returnedBook: "+returnedBook.getIsbn());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getIsbn()).isEqualTo(bookIsbn);
    }
}
