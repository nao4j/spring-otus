package ru.yegorpilipenko.otus.spring.m2h4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;

import java.util.Collection;

public interface BookRepository extends MongoRepository<Book, String>, ExtendedBookRepository {

    @Query("{}")
    Collection<ShortBook> findAllShort();

    int removeById(String id);

}
