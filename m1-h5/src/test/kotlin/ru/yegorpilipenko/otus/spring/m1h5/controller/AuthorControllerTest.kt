package ru.yegorpilipenko.otus.spring.m1h5.controller

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
import ru.yegorpilipenko.otus.spring.m1h5.model.Author
import ru.yegorpilipenko.otus.spring.m1h5.service.AuthorService

@ExtendWith(MockitoExtension::class)
class AuthorControllerTest : WithAssertions {

    @Mock
    lateinit var authorService: AuthorService
    lateinit var controller: AuthorController

    @BeforeEach
    fun setup() {
        controller = AuthorController(authorService)
    }

    @Nested
    inner class AuthorsTest {

        @Test
        fun shouldPassForMoreBooks() {
            doReturn(listOf(Author(1, "af1", "al1"), Author(2, "af2", "al2"))).whenever(authorService).findAll()
            assertThat(controller.authors()).containsExactlyInAnyOrder("1: af1 al1", "2: af2 al2")
        }

        @Test
        fun shouldPassWithoutBooks() {
            doReturn(emptyList<Author>()).whenever(authorService).findAll()
            assertThat(controller.authors()).isEmpty()
        }
    }

    @Nested
    inner class AuthorTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            doReturn(Author(id, "af", "al")).whenever(authorService).findById(id)
            assertThat(controller.author(id)).isEqualTo("1: af al")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(null).whenever(authorService).findById(id)
            assertThat(controller.author(id)).isEqualTo("Not found")
        }
    }

    @Nested
    inner class AuthorSaveTest {

        @Test
        fun shouldPassForSaveFullBook() {
            doReturn(Author(1, "af", "al")).whenever(authorService).save(any())
            assertThat(controller.authorSave("af", "al")).isEqualTo("1: af al")
            verify(authorService).save(Author(firstName = "af", lastName = "al"))
        }
    }

    @Nested
    inner class AuthorRemoveTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            doReturn(true).whenever(authorService).removeById(id)
            assertThat(controller.authorRemove(id)).isEqualTo("Removed")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(false).whenever(authorService).removeById(id)
            assertThat(controller.authorRemove(id)).isEqualTo("Not found")
        }
    }
}