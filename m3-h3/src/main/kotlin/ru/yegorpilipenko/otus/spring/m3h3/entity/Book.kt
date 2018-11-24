package ru.yegorpilipenko.otus.spring.m3h3.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "books", schema = "batch")
data class Book(

        @Id
        @GeneratedValue(generator = "books_id_generator")
        @SequenceGenerator(name = "books_id_generator", sequenceName = "books_sq", schema = "batch")
        val id: Long? = null,

        @Column(name = "name", nullable = false, unique = true)
        val name: String

)
