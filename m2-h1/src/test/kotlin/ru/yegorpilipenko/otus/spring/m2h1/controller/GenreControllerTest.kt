package ru.yegorpilipenko.otus.spring.m2h1.controller

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
import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre
import ru.yegorpilipenko.otus.spring.m2h1.service.GenreService

@ExtendWith(MockitoExtension::class)
class GenreControllerTest : WithAssertions {

    @Mock
    lateinit var genreService: GenreService
    lateinit var controller: GenreController

    @BeforeEach
    fun setup() {
        controller = GenreController(genreService)
    }

    @Nested
    inner class GenresTest {

        @Test
        fun shouldPassForMoreBooks() {
            doReturn(listOf(Genre(1, "b1"), Genre(2, "b2"))).whenever(genreService).findAll()
            assertThat(controller.genres()).containsExactlyInAnyOrder("1: b1", "2: b2")
        }

        @Test
        fun shouldPassWithoutBooks() {
            doReturn(emptyList<Genre>()).whenever(genreService).findAll()
            assertThat(controller.genres()).isEmpty()
        }
    }

    @Nested
    inner class GenreTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            doReturn(Genre(id, "b")).whenever(genreService).findById(id)
            assertThat(controller.genre(id)).isEqualTo("1: b")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(null).whenever(genreService).findById(id)
            assertThat(controller.genre(id)).isEqualTo("Not found")
        }
    }

    @Nested
    inner class GenreSaveTest {

        @Test
        fun shouldPassForSaveFullBook() {
            doReturn(Genre(1, "b")).whenever(genreService).save(any())
            assertThat(controller.genreSave("b")).isEqualTo("1: b")
            verify(genreService).save(Genre(name = "b"))
        }
    }

    @Nested
    inner class GenreRemoveTest {

        @Test
        fun shouldPassForExistsBook() {
            val id = 1L
            doReturn(true).whenever(genreService).removeById(id)
            assertThat(controller.genreRemove(id)).isEqualTo("Removed")
        }

        @Test
        fun shouldPassForNotExistsBook() {
            val id = 1L
            doReturn(false).whenever(genreService).removeById(id)
            assertThat(controller.genreRemove(id)).isEqualTo("Not found")
        }
    }
}