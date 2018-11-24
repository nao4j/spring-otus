package ru.yegorpilipenko.otus.spring.m1h5.service

import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h5.model.Book

interface BookService {

    fun findAll(): Collection<ShortBook>

    fun findById(id: Long): Book?

    fun save(book: ShortBook, authorIds: Collection<Long>, genreIds: Collection<Long>): Book

    fun removeById(id: Long): Boolean

}
