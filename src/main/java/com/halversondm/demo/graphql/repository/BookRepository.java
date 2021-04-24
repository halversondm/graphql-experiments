package com.halversondm.demo.graphql.repository;

import com.halversondm.demo.graphql.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
