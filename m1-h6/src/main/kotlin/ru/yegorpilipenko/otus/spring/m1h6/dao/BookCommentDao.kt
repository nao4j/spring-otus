package ru.yegorpilipenko.otus.spring.m1h6.dao

import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment

interface BookCommentDao {

    fun findById(id: Long): BookComment?

    fun save(bookComment: BookComment): BookComment

    fun deleteById(id: Long): Boolean
}