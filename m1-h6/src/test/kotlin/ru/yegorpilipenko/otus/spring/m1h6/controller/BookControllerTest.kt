package ru.yegorpilipenko.otus.spring.m1h6.controller

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
import org.mockito.junit.jupiter.MockitoExtension
import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Author
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre
import ru.yegorpilipenko.otus.spring.m1h6.service.BookService
import java.io.ByteArrayInputStream
import java.util.Scanner

@ExtendWith(MockitoExtension::class)
class BookControllerTest : WithAssertions {

    @Mock lateinit var bookService: BookService
    lateinit var scanner: Scanner
    lateinit var controller: BookController

    @BeforeEach
    fun setup() {
        scanner = Scanner(ByteArrayInputStream("""
            2 3
            4 5
        """.trimIndent().toByteArray()))
        controller = BookController(bookService, scanner)
    }

    @Nested
    inner class BooksTest {

        @Test
        fun shouldPassForMoreBooks() {
            doReturn(listOf(ShortBook(1, "b1"), ShortBook(2, "b2"))).whenever(bookService).findAll()
            assertThat(controller.books()).containsExactlyInAnyOrder("1: b1", "2: b2")
        }

        @Test
        fun shouldPassWithoutBooks() {
            doReturn(emptyList<ShortBook>()).whenever(bookService).findAll()
            assertThat(controller.books()).isEmpty()
        }
    }

    @Nested
    inner class BookTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            val author = Author(2, "af", "al")
            val genre = Genre(3, "g")
            val comment = BookComment(4, id, "test@email.com", "text")
            doReturn(Book(id, "b", listOf(author), listOf(genre), listOf(comment))).whenever(bookService).findById(id)
            assertThat(controller.book(id)).isEqualTo("""
                1: b
                Авторы:
                2: af al
                Жанры:
                3: g
                Комментарии:
                4: test@email.com
                text
            """.trimIndent())
        }

        @Test
        fun shouldPassForExistsShortBook() {
            val id = 1L
            doReturn(Book(id, "b", emptyList(), emptyList(), emptyList())).whenever(bookService).findById(id)
            assertThat(controller.book(id)).isEqualTo("1: b")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(null).whenever(bookService).findById(id)
            assertThat(controller.book(id)).isEqualTo("Not found")
        }
    }

    @Nested
    inner class BookSaveTest {

        @Test
        fun shouldPassForSaveFullBook() {
            doReturn(Book(
                    1,
                    "b",
                    listOf(Author(2, "af1", "al1"), Author(3, "af2", "al2")),
                    listOf(Genre(4, "g1"), Genre(5, "g2")),
                    emptyList()
            )).whenever(bookService).save(any(), any(), any())
            assertThat(controller.bookSave("b")).isEqualTo("""
                1: b
                Авторы:
                2: af1 al1
                3: af2 al2
                Жанры:
                4: g1
                5: g2
            """.trimIndent())
            verify(bookService).save(ShortBook(name = "b"), listOf(2L, 3L), listOf(4L, 5L))
        }
    }

    @Nested
    inner class BookRemoveTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            doReturn(true).whenever(bookService).removeById(id)
            assertThat(controller.bookRemove(id)).isEqualTo("Removed")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(false).whenever(bookService).removeById(id)
            assertThat(controller.bookRemove(id)).isEqualTo("Not found")
        }
    }
}
