package ru.yegorpilipenko.otus.spring.m3h3.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.yegorpilipenko.otus.spring.m3h3.entity.Book

interface BookJpaRepository: JpaRepository<Book, Long>
