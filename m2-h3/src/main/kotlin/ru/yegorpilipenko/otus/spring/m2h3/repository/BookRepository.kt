package ru.yegorpilipenko.otus.spring.m2h3.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.yegorpilipenko.otus.spring.m2h3.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m2h3.model.Book

interface BookRepository: MongoRepository<Book, String> {

    @Query("{}")
    fun findAllShort(): Collection<ShortBook>

    fun removeById(id: String): Int

}
