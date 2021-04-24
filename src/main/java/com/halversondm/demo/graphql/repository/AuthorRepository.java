package com.halversondm.demo.graphql.repository;

import com.halversondm.demo.graphql.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
