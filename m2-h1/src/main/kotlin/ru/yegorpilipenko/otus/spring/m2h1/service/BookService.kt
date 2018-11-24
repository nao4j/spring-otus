package ru.yegorpilipenko.otus.spring.m2h1.service

import ru.yegorpilipenko.otus.spring.m2h1.entity.Book

interface BookService {

    fun findAll(): Collection<Book>

    fun findById(id: Long): Book?

    fun save(name: String, authorIds: Collection<Long>, genreIds: Collection<Long>): Book

    fun removeById(id: Long): Boolean

}
