package ru.yegorpilipenko.otus.spring.m2h2.service;

import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;

import javax.annotation.Nonnull;

public interface CommentService {

    /**
     * @throws IllegalArgumentException если книга не найдена по id
     */
    @Nonnull
    Comment add(@Nonnull String bookId, @Nonnull Comment comment);

    /**
     * @throws IllegalArgumentException если книга не найдена по id
     */
    boolean remove(@Nonnull String bookId, @Nonnull final Comment comment);

}
