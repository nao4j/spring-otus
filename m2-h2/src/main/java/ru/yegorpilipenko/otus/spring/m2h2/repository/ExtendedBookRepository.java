package ru.yegorpilipenko.otus.spring.m2h2.repository;

import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface ExtendedBookRepository {

    @Nonnull
    Collection<Author> findAllAuthors();

    @Nonnull
    Collection<Genre> findAllGenres();

}
