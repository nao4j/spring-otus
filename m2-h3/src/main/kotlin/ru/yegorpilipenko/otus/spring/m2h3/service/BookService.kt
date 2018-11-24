package ru.yegorpilipenko.otus.spring.m2h3.service

import ru.yegorpilipenko.otus.spring.m2h3.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Comment

interface BookService {

    fun getAll(): Collection<ShortBook>

    fun getById(id: String): Book?

    fun save(book: Book): Book

    fun removeById(id: String): Boolean

    fun addComment(bookId: String, comment: Comment): Book

    fun removeComment(bookId: String, comment: Comment): Book?

}
