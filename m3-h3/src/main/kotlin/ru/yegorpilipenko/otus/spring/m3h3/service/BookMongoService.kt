package ru.yegorpilipenko.otus.spring.m3h3.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m3h3.document.Book
import ru.yegorpilipenko.otus.spring.m3h3.repository.BookMongoRepository

@Service
class BookMongoService(val bookRepository: BookMongoRepository): BookService<Book> {

    override fun getPage(pageable: Pageable): Page<Book> = bookRepository.findAll(pageable)

}
