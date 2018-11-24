package ru.yegorpilipenko.otus.spring.m1h6.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "GENRES", schema = "OTUS_LIBRARY")
data class Genre(

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = SEQUENCE, generator = "GENRES_ID_GENERATOR")
        @SequenceGenerator(
                name = "GENRES_ID_GENERATOR",
                sequenceName = "OTUS_LIBRARY.GENRES_ID_SQ",
                allocationSize = 1,
                initialValue = 1
        )
        var id: Long? = null,

        @Column(name = "NAME", nullable = false)
        val name: String

)
