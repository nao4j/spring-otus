package ru.yegorpilipenko.otus.spring.m2h1.service

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.yegorpilipenko.otus.spring.m2h1.entity.Author
import ru.yegorpilipenko.otus.spring.m2h1.entity.Book
import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre
import ru.yegorpilipenko.otus.spring.m2h1.repository.AuthorRepository
import ru.yegorpilipenko.otus.spring.m2h1.repository.BookRepository
import ru.yegorpilipenko.otus.spring.m2h1.repository.GenreRepository

@ExtendWith(MockitoExtension::class)
class BookServiceTest : WithAssertions {

    @Mock
    lateinit var bookRepository: BookRepository
    @Mock
    lateinit var authorRepository: AuthorRepository
    @Mock
    lateinit var genreRepository: GenreRepository
    lateinit var bookService: BookService

    @BeforeEach
    fun setup() {
        bookService = BookServiceImpl(bookRepository, authorRepository, genreRepository)
    }

    @Nested
    inner class SaveTest {

        @Test
        fun shouldPassForMoreBooks() {
            doReturn(listOf(Author(1, "f1", "l1"), Author(2, "f2", "l2")))
                    .whenever(authorRepository).findAllById(listOf(1, 2))
            doReturn(listOf(Genre(3, "g1"), Genre(4, "g1"))).whenever(genreRepository).findAllById(listOf(3, 4))
            doReturn(Book(name = "test")).whenever(bookRepository).save(Mockito.any(Book::class.java))
            bookService.save("testBook", listOf(1, 2), listOf(3, 4))
            verify(bookRepository).save(Book(
                    name = "testBook",
                    authors = listOf(Author(1, "f1", "l1"), Author(2, "f2", "l2")),
                    genres = listOf(Genre(3, "g1"), Genre(4, "g1"))
            ))
        }
    }

}
