package ru.yegorpilipenko.otus.spring.m1h6.dao

import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

interface GenreDao {

    fun findAll(): Collection<Genre>

    fun findAll(ids: Collection<Long>): Collection<Genre>

    fun findById(id: Long): Genre?

    fun save(genre: Genre): Genre

    fun deleteById(id: Long): Boolean
}
