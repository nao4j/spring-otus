package ru.yegorpilipenko.otus.spring.m1h5.dao

import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h5.model.Book

interface BookDao {

    fun findAll(): Collection<ShortBook>

    fun findById(id: Long): Book?

    fun save(book: Book): Book

    fun removeById(id: Long): Boolean
}
