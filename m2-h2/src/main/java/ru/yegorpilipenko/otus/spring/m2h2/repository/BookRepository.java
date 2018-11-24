package ru.yegorpilipenko.otus.spring.m2h2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;

import javax.annotation.Nonnull;

public interface BookRepository extends MongoRepository<Book, String>, ExtendedBookRepository {

    int removeById(@Nonnull String id);

}
