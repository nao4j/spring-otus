package ru.yegorpilipenko.otus.spring.m2h4.service;

import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Collection<ShortBook> getAll();

    Optional<Book> getById(String id);

    Book save(Book book);

    boolean removeById(String id);

    Book addComment(String bookId, Comment comment);

    Optional<Book> removeComment(String bookId, Comment comment);

}
