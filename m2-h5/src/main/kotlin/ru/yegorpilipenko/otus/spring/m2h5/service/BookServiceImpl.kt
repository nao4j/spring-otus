package ru.yegorpilipenko.otus.spring.m2h5.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yegorpilipenko.otus.spring.m2h5.domain.Book
import ru.yegorpilipenko.otus.spring.m2h5.repository.BookRepository

@Service
class BookServiceImpl(val bookRepository: BookRepository): BookService {

    override fun getAll(): Flux<Book> = bookRepository.findAll()

    override fun getById(id: String): Mono<Book> = bookRepository.findById(id)

    override fun save(book: Book): Mono<Book> = bookRepository.save(book)

    override fun removeById(id: String): Mono<Void> = bookRepository.deleteById(id)

}
