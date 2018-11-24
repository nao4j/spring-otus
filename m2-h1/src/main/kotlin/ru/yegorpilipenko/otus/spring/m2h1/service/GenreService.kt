package ru.yegorpilipenko.otus.spring.m2h1.service

import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre

interface GenreService {

    fun findAll(): Collection<Genre>

    fun findById(id: Long): Genre?

    fun save(genre: Genre): Genre

    fun removeById(id: Long): Boolean
}