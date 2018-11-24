package ru.yegorpilipenko.otus.spring.m3h3.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yegorpilipenko.otus.spring.m3h3.entity.Book
import ru.yegorpilipenko.otus.spring.m3h3.service.BookService

@RestController
@RequestMapping("/jpa/books")
class BookJpaController(val bookService: BookService<Book>) {

    @GetMapping
    fun getAuthorPage(pageable: Pageable) = bookService.getPage(pageable)

}
