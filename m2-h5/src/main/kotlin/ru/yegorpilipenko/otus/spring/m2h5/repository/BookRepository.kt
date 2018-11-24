package ru.yegorpilipenko.otus.spring.m2h5.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import ru.yegorpilipenko.otus.spring.m2h5.domain.Book

interface BookRepository: ReactiveMongoRepository<Book, String>
