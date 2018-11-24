package ru.yegorpilipenko.otus.spring.m1h5.dao

import ru.yegorpilipenko.otus.spring.m1h5.model.Author

interface AuthorDao {

    fun findAll(): Collection<Author>

    fun findAll(ids: Iterable<Long>): Collection<Author>

    fun findById(id: Long): Author?

    fun findByBookId(bookId: Long): Collection<Author>

    fun save(author: Author): Author

    fun removeById(id: Long): Boolean
}
