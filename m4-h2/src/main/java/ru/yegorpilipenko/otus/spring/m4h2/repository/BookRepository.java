package ru.yegorpilipenko.otus.spring.m4h2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.yegorpilipenko.otus.spring.m4h2.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m4h2.model.Book;

import java.util.Collection;

public interface BookRepository extends MongoRepository<Book,String>, ExtendedBookRepository {

    @Query("{}")
    Collection<ShortBook> findAllShort();

    int removeById(String id);

}
