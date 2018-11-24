package ru.yegorpilipenko.otus.spring.m1h6.dao

import ru.yegorpilipenko.otus.spring.m1h6.entity.Author

interface AuthorDao {

    fun findAll(): Collection<Author>

    fun findAll(ids: Collection<Long>): Collection<Author>

    fun findById(id: Long): Author?

    fun save(author: Author): Author

    fun deleteById(id: Long): Boolean
}
