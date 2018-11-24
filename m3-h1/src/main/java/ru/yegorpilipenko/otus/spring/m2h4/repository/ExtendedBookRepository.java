package ru.yegorpilipenko.otus.spring.m2h4.repository;

import ru.yegorpilipenko.otus.spring.m2h4.model.Author;
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;

import java.util.Collection;

public interface ExtendedBookRepository {

    Collection<Author> findAllAuthors();

    Collection<Genre> findAllGenres();

}
