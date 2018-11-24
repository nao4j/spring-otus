package ru.yegorpilipenko.otus.spring.m2h3.service

import ru.yegorpilipenko.otus.spring.m2h3.model.Author

interface AuthorService {

    fun getAll(): Collection<Author>

}
