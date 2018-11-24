package ru.yegorpilipenko.otus.spring.m2h3.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Genre
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository

@Service
class GenreServiceImpl(val bookRepository: BookRepository): GenreService {

    override fun getAll(): Collection<Genre> = bookRepository.findAll().flatMap(Book::genres).toSet()

}
