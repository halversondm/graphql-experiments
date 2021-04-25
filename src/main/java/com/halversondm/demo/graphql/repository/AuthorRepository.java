package com.halversondm.demo.graphql.repository;

import com.halversondm.demo.graphql.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {

    List<Author> findByLastNameIsLike(String lastName);
}
