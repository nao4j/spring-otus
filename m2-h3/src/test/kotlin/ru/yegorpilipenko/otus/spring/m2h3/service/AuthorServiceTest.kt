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
import ru.yegorpilipenko.otus.spring.m2h3.model.Author
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository

@ExtendWith(MockitoExtension::class)
class AuthorServiceTest: WithAssertions {

    @Mock
    lateinit var bookRepository: BookRepository
    lateinit var authorService: AuthorService

    @BeforeEach
    fun setup() {
        authorService = AuthorServiceImpl(bookRepository)
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun shouldPassForNotEmptyBooks() {
            doReturn(listOf(
                    Book(name = "book1", authors = listOf(Author("f1", "l1"), Author("f2", "l2"))),
                    Book(name = "book2", authors = listOf(Author("f1", "l1")))
            )).whenever(bookRepository).findAll()
            assertThat(authorService.getAll()).containsExactlyInAnyOrder(Author("f1", "l1"), Author("f2", "l2"))
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForEmptyBooks() {
            doReturn(emptyList<Book>()).whenever(bookRepository).findAll()
            assertThat(authorService.getAll()).isEmpty()
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForNotExists() {
            doReturn(listOf(Book(name = "book1"), Book(name = "book2"))).whenever(bookRepository).findAll()
            assertThat(authorService.getAll()).isEmpty()
            verify(bookRepository).findAll()
            verifyNoMoreInteractions(bookRepository)
        }

    }

}
