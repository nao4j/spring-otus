package ru.yegorpilipenko.otus.spring.m3h3.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.yegorpilipenko.otus.spring.m3h3.document.Book

interface BookMongoRepository: MongoRepository<Book, String>
