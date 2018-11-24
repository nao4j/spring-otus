package ru.yegorpilipenko.otus.spring.m3h2.repository;

import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.yegorpilipenko.otus.spring.m3h2.model.Author;
import ru.yegorpilipenko.otus.spring.m3h2.model.Genre;

import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@RequiredArgsConstructor
public final class ExtendedBookRepositoryImpl implements ExtendedBookRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Collection<Author> findAllAuthors() {
        val spliterator = mongoTemplate.getCollection("books").distinct("authors", DBObject.class).spliterator();
        return stream(spliterator, false)
                .map(dbObject -> new Author(
                        (String) dbObject.get("firstName"),
                        (String) dbObject.get("lastName")
                )).collect(toList());
    }

    @Override
    public Collection<Genre> findAllGenres() {
        val spliterator = mongoTemplate.getCollection("books").distinct("genres", DBObject.class).spliterator();
        return stream(spliterator, false)
                .map(dbObject -> new Genre((String) dbObject.get("name")))
                .collect(toList());
    }

}
