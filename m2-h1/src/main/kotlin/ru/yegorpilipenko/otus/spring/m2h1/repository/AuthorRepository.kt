package ru.yegorpilipenko.otus.spring.m2h1.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m2h1.entity.Author

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {

    fun removeById(id: Long): Int
}
