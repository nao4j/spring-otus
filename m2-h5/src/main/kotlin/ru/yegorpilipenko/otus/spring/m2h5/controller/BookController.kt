package ru.yegorpilipenko.otus.spring.m2h5.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yegorpilipenko.otus.spring.m2h5.domain.Book
import ru.yegorpilipenko.otus.spring.m2h5.service.BookService

@RestController
@RequestMapping("/books")
class BookController(val bookService: BookService) {

    @GetMapping
    fun getAll(): Flux<Book> = bookService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): Mono<Book> = bookService.getById(id)

    @PostMapping
    fun save(@RequestBody book: Book): Mono<Book> = bookService.save(book)

    @DeleteMapping("{id}")
    fun removeById(@PathVariable id: String): Mono<Void> = bookService.removeById(id)

}
