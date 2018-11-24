package ru.yegorpilipenko.otus.spring.m2h1.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption.FALSE
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "BOOKS", schema = "OTUS_LIBRARY")
data class Book(

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = SEQUENCE, generator = "BOOKS_ID_GENERATOR")
        @SequenceGenerator(
                name = "BOOKS_ID_GENERATOR",
                sequenceName = "OTUS_LIBRARY.BOOKS_ID_SQ",
                allocationSize = 1,
                initialValue = 1
        )
        var id: Long? = null,

        @Column(name = "NAME", nullable = false)
        val name: String,

        @LazyCollection(FALSE)
        @ManyToMany(cascade = [MERGE, PERSIST])
        @JoinTable(
                name = "BOOKS_AUTHORS",
                schema = "OTUS_LIBRARY",
                joinColumns = [JoinColumn(name = "BOOK_ID")],
                inverseJoinColumns = [JoinColumn(name = "AUTHOR_ID")]
        )
        val authors: Collection<Author> = emptyList(),

        @LazyCollection(FALSE)
        @ManyToMany(cascade = [MERGE, PERSIST])
        @JoinTable(
                name = "BOOKS_GENRES",
                schema = "OTUS_LIBRARY",
                joinColumns = [JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)],
                inverseJoinColumns = [JoinColumn(name = "GENRE_ID", insertable = false, updatable = false)]
        )
        val genres: Collection<Genre> = emptyList(),

        @LazyCollection(FALSE)
        @OneToMany(cascade = [MERGE, PERSIST])
        @JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)
        val comments: Collection<BookComment> = emptyList()

)
