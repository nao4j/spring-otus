package ru.yegorpilipenko.otus.spring.m2h2.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

import static java.util.Collections.emptyList;
import static lombok.AccessLevel.PRIVATE;

@Data
@RequiredArgsConstructor
@Document(collection = "books")
@NoArgsConstructor(access = PRIVATE, force = true)
public final class Book {

    @Nullable
    private final String id;
    @Nonnull
    private final String name;
    @Nonnull
    private final Collection<Author> authors;
    @Nonnull
    private final Collection<Genre> genres;
    @Nonnull
    private final Collection<Comment> comments;

    public Book(@Nonnull final String id, @Nonnull String name) {
        this.id = id;
        this.name = name;
        this.authors = emptyList();
        this.genres = emptyList();
        this.comments = emptyList();
    }

    public Book(
            @Nonnull final String name,
            @Nonnull final Collection<Author> authors,
            @Nonnull final Collection<Genre> genres
    ) {
        this.id = null;
        this.name = name;
        this.authors = authors;
        this.genres = genres;
        this.comments = emptyList();
    }

}
