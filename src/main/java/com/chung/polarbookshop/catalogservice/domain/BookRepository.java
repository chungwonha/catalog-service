package com.chung.polarbookshop.catalogservice.domain;

import java.util.*;

public interface BookRepository {
    Collection<Book> findAllOrderByTitle();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    Book delete(Book book);
    Book update(String isbn, Book book);
}
