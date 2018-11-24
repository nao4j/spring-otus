package ru.yegorpilipenko.otus.spring.m2h3.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class Book(
        @Id val id: String? = null,
        val name: String,
        val authors: Collection<Author> = emptyList(),
        val genres: Collection<Genre> = emptyList(),
        val comments: Collection<Comment> = emptyList()
)
