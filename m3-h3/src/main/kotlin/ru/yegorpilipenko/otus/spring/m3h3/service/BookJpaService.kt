package ru.yegorpilipenko.otus.spring.m3h3.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m3h3.entity.Book
import ru.yegorpilipenko.otus.spring.m3h3.repository.BookJpaRepository

@Service
class BookJpaService(val bookRepository: BookJpaRepository) : BookService<Book> {

    override fun getPage(pageable: Pageable): Page<Book> = bookRepository.findAll(pageable)

}
