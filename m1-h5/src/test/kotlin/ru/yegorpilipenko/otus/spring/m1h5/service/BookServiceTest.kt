package ru.yegorpilipenko.otus.spring.m1h5.service

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
import ru.yegorpilipenko.otus.spring.m1h5.dao.AuthorDao
import ru.yegorpilipenko.otus.spring.m1h5.dao.BookDao
import ru.yegorpilipenko.otus.spring.m1h5.dao.GenreDao
import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h5.model.Author
import ru.yegorpilipenko.otus.spring.m1h5.model.Book
import ru.yegorpilipenko.otus.spring.m1h5.model.Genre

@ExtendWith(MockitoExtension::class)
class BookServiceTest : WithAssertions {

    @Mock lateinit var bookDao: BookDao
    @Mock lateinit var authorDao: AuthorDao
    @Mock lateinit var genreDao: GenreDao
    lateinit var bookService: BookService

    @BeforeEach
    fun setup() {
        bookService = BookServiceImpl(bookDao, authorDao, genreDao)
    }

    @Nested
    inner class SaveTest {

        @Test
        fun shouldPassForMoreBooks() {
            doReturn(listOf(Author(1, "f1", "l1"), Author(2, "f2", "l2"))).whenever(authorDao).findAll(listOf(1, 2))
            doReturn(listOf(Genre(3, "g1"), Genre(4, "g1"))).whenever(genreDao).findAll(listOf(3, 4))
            bookService.save(ShortBook(5, "testBook"), listOf(1, 2), listOf(3, 4))
            verify(bookDao).save(Book(
                    5,
                    "testBook",
                    listOf(Author(1, "f1", "l1"), Author(2, "f2", "l2")),
                    listOf(Genre(3, "g1"), Genre(4, "g1"))
            ))
        }
    }

}
