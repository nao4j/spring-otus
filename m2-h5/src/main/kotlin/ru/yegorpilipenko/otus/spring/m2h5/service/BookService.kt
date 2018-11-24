package ru.yegorpilipenko.otus.spring.m2h5.service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yegorpilipenko.otus.spring.m2h5.domain.Book

interface BookService {

    fun getAll(): Flux<Book>

    fun getById(id: String): Mono<Book>

    fun save(book: Book): Mono<Book>

    fun removeById(id: String): Mono<Void>

}
