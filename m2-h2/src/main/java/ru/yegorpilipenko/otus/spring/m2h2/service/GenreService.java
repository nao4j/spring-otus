package ru.yegorpilipenko.otus.spring.m2h2.service;

import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface GenreService {

    @Nonnull
    Collection<Genre> getAll();

}
