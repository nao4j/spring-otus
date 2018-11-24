package ru.yegorpilipenko.otus.spring.m3h3.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yegorpilipenko.otus.spring.m3h3.document.Book
import ru.yegorpilipenko.otus.spring.m3h3.service.BookService

@RestController
@RequestMapping("/mongo/books")
class BookMongoController(val bookService: BookService<Book>) {

    @GetMapping
    fun getPage(pageable: Pageable) = bookService.getPage(pageable)

}
