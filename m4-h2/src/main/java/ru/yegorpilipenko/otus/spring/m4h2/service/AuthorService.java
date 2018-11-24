package ru.yegorpilipenko.otus.spring.m4h2.service;

import ru.yegorpilipenko.otus.spring.m4h2.model.Author;

import java.util.Collection;

public interface AuthorService {

    Collection<Author> getAll();

}
