package ru.yegorpilipenko.otus.spring.m1h6.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "BOOK_COMMENTS", schema = "OTUS_LIBRARY")
data class BookComment(

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = SEQUENCE, generator = "BOOK_COMMENTS_ID_GENERATOR")
        @SequenceGenerator(
                name = "BOOK_COMMENTS_ID_GENERATOR",
                sequenceName = "OTUS_LIBRARY.BOOK_COMMENTS_ID_SQ",
                allocationSize = 1,
                initialValue = 1
        )
        var id: Long? = null,

        @Column(name = "BOOK_ID", nullable = false)
        val bookId: Long,

        @Column(name = "EMAIL", nullable = false)
        val email: String,

        @Column(name = "TEXT", nullable = false)
        val text: String

)
