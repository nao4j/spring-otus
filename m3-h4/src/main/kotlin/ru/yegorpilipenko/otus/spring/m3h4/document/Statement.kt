package ru.yegorpilipenko.otus.spring.m3h4.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "statements")
data class Statement(@Id val id: String? = null, val clientName: String, val text: String)
