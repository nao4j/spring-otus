package ru.yegorpilipenko.otus.spring.m3h2.repository;

import ru.yegorpilipenko.otus.spring.m3h2.model.Author;
import ru.yegorpilipenko.otus.spring.m3h2.model.Genre;

import java.util.Collection;

public interface ExtendedBookRepository {

    Collection<Author> findAllAuthors();

    Collection<Genre> findAllGenres();

}
