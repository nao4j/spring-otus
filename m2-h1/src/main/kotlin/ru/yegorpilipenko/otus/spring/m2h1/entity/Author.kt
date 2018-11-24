package ru.yegorpilipenko.otus.spring.m2h1.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "AUTHORS", schema = "OTUS_LIBRARY")
data class Author(

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = SEQUENCE, generator = "AUTHORS_ID_GENERATOR")
        @SequenceGenerator(
                name = "AUTHORS_ID_GENERATOR",
                sequenceName = "OTUS_LIBRARY.AUTHORS_ID_SQ",
                allocationSize = 1,
                initialValue = 1
        )
        var id: Long? = null,

        @Column(name = "FIRST_NAME", nullable = false)
        val firstName: String,

        @Column(name = "LAST_NAME", nullable = false)
        val lastName: String

)
