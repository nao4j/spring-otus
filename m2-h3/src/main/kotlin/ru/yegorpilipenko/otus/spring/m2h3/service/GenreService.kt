package ru.yegorpilipenko.otus.spring.m2h3.service

import ru.yegorpilipenko.otus.spring.m2h3.model.Genre

interface GenreService {

    fun getAll(): Collection<Genre>

}
