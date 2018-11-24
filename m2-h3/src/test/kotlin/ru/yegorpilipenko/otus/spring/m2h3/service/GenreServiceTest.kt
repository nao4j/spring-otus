package ru.yegorpilipenko.otus.spring.m2h3.service

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.jupiter.MockitoExtension
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Genre
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository

@ExtendWith(MockitoExtension::class)
class GenreServiceTest: WithAssertions {

    @Mock
    lateinit var bookRepository: BookRepository
    lateinit var genreService: GenreService

    @BeforeEach
    fun setup() {
        genreService = GenreServiceImpl(bookRepository)
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun shouldPassForNotEmptyBooks() {
            doReturn(listOf(
                    Book(name = "book1", genres = listOf(Genre("1"), Genre("2"))),
                    Book(name = "book2", genres = listOf(Genre("1")))
            )).whenever(bookRepository).findAll()
            assertThat(genreService.getAll()).containsExactlyInAnyOrder(Genre("1"), Genre("2"))
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForEmptyBooks() {
            doReturn(emptyList<Book>()).whenever(bookRepository).findAll()
            assertThat(genreService.getAll()).isEmpty()
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForNotExists() {
            doReturn(listOf(Book(name = "book1"), Book(name = "book2"))).whenever(bookRepository).findAll()
            assertThat(genreService.getAll()).isEmpty()
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

    }

}
