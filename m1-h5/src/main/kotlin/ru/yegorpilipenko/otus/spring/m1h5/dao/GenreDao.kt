package ru.yegorpilipenko.otus.spring.m1h5.dao

import ru.yegorpilipenko.otus.spring.m1h5.model.Genre

interface GenreDao {

    fun findAll(): Collection<Genre>

    fun findAll(ids: Iterable<Long>): Collection<Genre>

    fun findById(id: Long): Genre?

    fun findByBookId(bookId: Long): Collection<Genre>

    fun save(genre: Genre): Genre

    fun removeById(id: Long): Boolean
}
