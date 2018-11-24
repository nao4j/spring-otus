package ru.yegorpilipenko.otus.spring.m1h6.service

import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

interface GenreService {

    fun findAll(): Collection<Genre>

    fun findById(id: Long): Genre?

    fun save(genre: Genre): Genre

    fun removeById(id: Long): Boolean
}