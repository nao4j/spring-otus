package ru.yegorpilipenko.otus.spring.m2h4.resource;

import lombok.NonNull;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;
import ru.yegorpilipenko.otus.spring.m2h4.model.Author;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;

import java.util.Collection;

@Value
public final class BookResource extends ResourceSupport {

    @NonNull String name;
    @NonNull Collection<Author> authors;
    @NonNull Collection<Genre> genres;
    @NonNull Collection<Comment> comments;

}
