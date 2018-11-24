package ru.yegorpilipenko.otus.spring.m1h6.dao

import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book

interface BookDao {

    fun findAll(): Collection<ShortBook>

    fun findById(id: Long): Book?

    fun save(book: Book): Book

    fun deleteById(id: Long): Boolean
}
