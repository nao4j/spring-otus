package ru.yegorpilipenko.otus.spring.m2h3.service

import com.nhaarman.mockito_kotlin.any
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
import ru.yegorpilipenko.otus.spring.m2h3.model.Comment
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class BookServiceTest: WithAssertions {

    @Mock
    lateinit var bookRepository: BookRepository
    lateinit var bookService: BookService

    @BeforeEach
    fun setup() {
        bookService = BookServiceImpl(bookRepository)
    }

    @Nested
    inner class RemoveByIdTest {

        @Test
        fun shouldPassForExists() {
            doReturn(1).whenever(bookRepository).removeById(any())
            assertThat(bookService.removeById("1")).isTrue()
            verify(bookRepository).removeById("1")
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForNotExists() {
            assertThat(bookService.removeById("1")).isFalse()
            verify(bookRepository).removeById("1")
            verifyNoMoreInteractions(bookRepository)
        }

    }

    @Nested
    inner class AddCommentTest {

        @Test
        fun shouldPassForExistsBook() {
            val bookId = "1"
            doReturn(Optional.of(Book(
                    id = bookId,
                    name = "testBook",
                    comments = listOf(Comment("e1", "t1"), Comment("e2", "t2"))
            ))).whenever(bookRepository).findById(bookId)
            doReturn(Book(name = "stub")).whenever(bookRepository).save(any<Book>())
            assertThat(bookService.addComment(bookId, Comment("e3", "t3"))).isEqualTo(Book(name = "stub"))
            verify(bookRepository).findById(bookId)
            verify(bookRepository).save(Book(
                    id = bookId,
                    name = "testBook",
                    comments = listOf(Comment("e1", "t1"), Comment("e2", "t2"), Comment("e3", "t3"))
            ))
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldFailForNotExistsBook() {
            val bookId = "1"
            doReturn(Optional.empty<Book>()).whenever(bookRepository).findById(bookId)
            assertThatThrownBy { bookService.addComment(bookId, Comment("e1", "t1")) }
                    .isInstanceOf(IllegalArgumentException::class.java)
            verify(bookRepository).findById(bookId)
            verifyNoMoreInteractions(bookRepository)
        }

    }

    @Nested
    inner class RemoveCommentTest {

        @Test
        fun shouldPassForExistsComment() {
            val bookId = "1"
            doReturn(Optional.of(Book(
                    id = bookId,
                    name = "testBook",
                    comments = listOf(Comment("e1", "t1"), Comment("e2", "t2"))
            ))).whenever(bookRepository).findById(bookId)
            doReturn(Book(name = "stub")).whenever(bookRepository).save(any<Book>())
            assertThat(bookService.removeComment(bookId, Comment("e1", "t1"))).isEqualTo(Book(name = "stub"))
            verify(bookRepository).findById(bookId)
            verify(bookRepository).save(Book(
                    id = bookId,
                    name = "testBook",
                    comments = listOf(Comment("e2", "t2"))
            ))
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldPassForNotExistsComment() {
            val bookId = "1"
            doReturn(Optional.of(Book(
                    id = bookId,
                    name = "testBook",
                    comments = listOf(Comment("e1", "t1"), Comment("e2", "t2"))
            ))).whenever(bookRepository).findById(bookId)
            assertThat(bookService.removeComment(bookId, Comment("e3", "t3"))).isNull()
            verify(bookRepository).findById(bookId)
            verifyNoMoreInteractions(bookRepository)
        }

        @Test
        fun shouldFailForNotExistsBook() {
            val bookId = "1"
            doReturn(Optional.empty<Book>()).whenever(bookRepository).findById(bookId)
            assertThatThrownBy { bookService.removeComment(bookId, Comment("e1", "t1")) }
                    .isInstanceOf(IllegalArgumentException::class.java)
            verify(bookRepository).findById(bookId)
            verifyNoMoreInteractions(bookRepository)
        }

    }

}