package ru.yegorpilipenko.otus.spring.m2h1.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m2h1.entity.Book

@Repository
interface BookRepository : JpaRepository<Book, Long> {

    fun removeById(id: Long): Int
}