package ru.yegorpilipenko.otus.spring.m2h1.service

import ru.yegorpilipenko.otus.spring.m2h1.entity.Author

interface AuthorService {

    fun findAll(): Collection<Author>

    fun findById(id: Long): Author?

    fun save(author: Author): Author

    fun removeById(id: Long): Boolean
}