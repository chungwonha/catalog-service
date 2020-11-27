package com.chung.polarbookshop.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import javax.persistence.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b order by b.title asc")
    Collection<Book> findAllOrderByTitle();

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);

}
