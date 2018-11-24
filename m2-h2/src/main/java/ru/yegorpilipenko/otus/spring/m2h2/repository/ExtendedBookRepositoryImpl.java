package ru.yegorpilipenko.otus.spring.m2h2.repository;

import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class ExtendedBookRepositoryImpl implements ExtendedBookRepository {

    private final MongoTemplate mongoTemplate;

    @Nonnull
    @Override
    public Collection<Author> findAllAuthors() {
        final Collection<Author> result = new ArrayList<>();
        for (final DBObject object : mongoTemplate.getCollection("books").distinct("authors", DBObject.class)) {
            result.add(new Author((String) object.get("firstName"), (String) object.get("lastName")));
        }
        return result;
    }

    @Nonnull
    @Override
    public Collection<Genre> findAllGenres() {
        final Collection<Genre> result = new ArrayList<>();
        for (final DBObject object : mongoTemplate.getCollection("books").distinct("genres", DBObject.class)) {
            result.add(new Genre((String) object.get("name")));
        }
        return result;
    }


}
