package ru.yegorpilipenko.otus.spring.m2h2.service;

import ru.yegorpilipenko.otus.spring.m2h2.document.Author;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface AuthorService {

    @Nonnull
    Collection<Author> getAll();

}
