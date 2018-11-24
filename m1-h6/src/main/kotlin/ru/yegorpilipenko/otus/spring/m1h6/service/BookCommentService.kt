package ru.yegorpilipenko.otus.spring.m1h6.service

import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment

interface BookCommentService {

    fun findById(id: Long): BookComment?

    fun save(bookComment: BookComment): BookComment

    fun removeById(id: Long): Boolean
}
