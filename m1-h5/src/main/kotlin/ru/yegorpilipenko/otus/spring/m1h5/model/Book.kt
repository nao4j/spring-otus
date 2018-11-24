package ru.yegorpilipenko.otus.spring.m1h5.model

data class Book(val id: Long, val name: String, val authors: Collection<Author>, val genres: Collection<Genre>)
