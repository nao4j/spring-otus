package ru.yegorpilipenko.otus.spring.m2h3.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m2h3.model.Author
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository

@Service
class AuthorServiceImpl(val bookRepository: BookRepository): AuthorService {

    override fun getAll(): Collection<Author> = bookRepository.findAll().flatMap(Book::authors).toSet()

}
