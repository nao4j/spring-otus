package ru.yegorpilipenko.otus.spring.m2h3.repository

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.yegorpilipenko.otus.spring.m2h3.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m2h3.model.Author
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Comment
import ru.yegorpilipenko.otus.spring.m2h3.model.Genre

@DataMongoTest
@ExtendWith(SpringExtension::class)
class BookRepositoryTest: WithAssertions {

    @Autowired
    lateinit var bookRepository: BookRepository

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
    }

    @Nested
    inner class FindAllShortTest {

        @Test
        fun shouldPassForExistsBooks() {
            val book1Id = bookRepository.save(Book(
                    name = "b1",
                    authors = listOf(Author("f1", "l1"), Author("f2", "l2")),
                    genres = listOf(Genre("n1")),
                    comments = listOf(Comment("e1", "t1"))
            )).id
            val book2Id = bookRepository.save(Book(
                    name = "b2",
                    authors = listOf(Author("f3", "l3")),
                    genres = listOf(Genre("n2"))
            )).id
            assertThat(bookRepository.findAllShort())
                    .containsExactlyInAnyOrder(ShortBook(book1Id, "b1"), ShortBook(book2Id, "b2"))
        }

        @Test
        fun shouldPassForNotExistsBooks() {
            assertThat(bookRepository.findAllShort()).isEmpty()
        }

    }

    @Nested
    inner class RemoveByIdTest {

        @Test
        fun shouldPassForExistsBook() {
            val savedBook = bookRepository.save(Book(name = "Book for test"))
            assertThat(bookRepository.removeById(savedBook.id!!)).isEqualTo(1)
        }

        @Test
        fun shouldPassForNotExistsBook() {
            assertThat(bookRepository.removeById("1")).isEqualTo(0)
        }

    }

}
