package ru.yegorpilipenko.otus.spring.m2h5.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Book(@Id val id: String? = null, val name: String)
