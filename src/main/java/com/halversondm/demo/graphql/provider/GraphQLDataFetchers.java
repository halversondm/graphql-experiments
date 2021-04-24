package com.halversondm.demo.graphql.provider;

import com.halversondm.demo.graphql.entity.Author;
import com.halversondm.demo.graphql.entity.Book;
import com.halversondm.demo.graphql.repository.AuthorRepository;
import com.halversondm.demo.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookRepository.findById(bookId).orElse(null);
        };
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return authorRepository.findById(book.getAuthorId()).orElse(null);
        };
    }
}
