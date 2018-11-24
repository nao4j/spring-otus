package ru.yegorpilipenko.otus.spring.m3h3.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class Book(@Id val id: String? = null, val name: String)
