package ru.yegorpilipenko.otus.spring.m2h2.service;

import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

public interface BookService {

    @Nonnull
    Collection<Book> getAll();

    @Nonnull
    Optional<Book> getById(@Nonnull String id);

    @Nonnull
    Book save(@Nonnull String name, @Nonnull Collection<Author> authors, @Nonnull Collection<Genre> genres);

    boolean remove(@Nonnull String id);

}
